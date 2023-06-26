import React, { Component } from "react";

import Card from "../../components/Card";
import FormGroup from "../../components/FormGroup";
import SelectMenu from "../../components/SelectMenu";
import LancamentosTable from "./LancamentosTable";

import LacamentoService from "../../app/service/lacamentoService";
import LocalStorageService from "../../app/service/localStoregeService";
import * as messages from "../../components/toastr";

import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';

class ConsultaLacamentos extends Component {
  state = {
    ano: "",
    mes: "",
    tipo: "",
    descricao: "",
    showConfirmDialog: false,
    lancamentoDeletar: {},
    lancamentos: [],
  };

  constructor() {
    super();
    this.service = new LacamentoService();
  }

  buscar = () => {
    if (!this.state.ano) {
      messages.mensagemErro("O preenchimento do campo Ano é obrigatótio.");
      return false;
    }

    const usuarioLogado = LocalStorageService.obterItem("_usuario_logado");

    const lancamentoFiltro = {
      ano: this.state.ano,
      mes: this.state.mes,
      tipo: this.state.tipo,
      descricao: this.state.descricao,
      usuario: usuarioLogado.id,
    };

    this.service
      .consulta(lancamentoFiltro)
      .then((response) => {
        this.setState({ lancamentos: response.data });
      })
      .catch((error) => {
        messages.mensagemErro(error.response.data);
      });
  };

  editar = (id) => {
    console.log("Editando", id);
  };

  abrirConfirmacao = (lancamento) =>{
    this.setState({showConfirmDialog: true, lancamentoDeletar: lancamento})
  }

  deletar = () => {
    this.service
      .deletar(this.state.lancamentoDeletar.id)
      .then((response) => {
        const lancamentos = this.state.lancamentos;
        const index = lancamentos.indexOf(this.lancamentoDeletar);
        lancamentos.splice(index, 1);
        this.setState({ lancamentos: lancamentos ,showConfirmDialog: false});
        messages.mensagemSucesso("Lançamento deletado com sucesso");
      })
      .catch((error) => {
        messages.mensagemErro(
          "Ocorreu um erro ao tentar deletar um lançamento"
        );
      });
  };

  cancelarDelecao = () =>{
    this.setState({showConfirmDialog: false, lancamentoDeletar: {}})
  }

  render() {
    const meses = this.service.obterListaMeses();
    const tipos = this.service.obterListasTipos();

    const confirmFooter = (
      <div>
          <Button label="Confirma" icon="pi pi-check" onClick={this.deletar} className="p-button-text" />
          <Button label="Cancelar" icon="pi pi-times" onClick={this.cancelarDelecao} autoFocus />
      </div>
  );

    return (
      <Card title="Consulta Lançamentos">
        <div className="row">
          <div className="col-md-6">
            <div className="bs-component">
              <FormGroup htmlFor="inputAno" label="Ano: *">
                <input
                  type="text"
                  className="form-control"
                  id="inputAno"
                  value={this.state.ano}
                  onChange={(e) => this.setState({ ano: e.target.value })}
                  placeholder="Digite o Ano"
                />
              </FormGroup>

              <FormGroup htmlFor="inputMes" label="Mês: *">
                <SelectMenu
                  value={this.state.mes}
                  onChange={(e) => this.setState({ mes: e.target.value })}
                  id="inputMes"
                  className="form-control"
                  lista={meses}
                />
              </FormGroup>

              <FormGroup htmlFor="inputDesc" label="Descrição: *">
                <input
                  value={this.state.descricao}
                  onChange={(e) => this.setState({ descricao: e.target.value })}
                  id="inputDesc"
                  placeholder="Digite a descrição"
                  className="form-control"
                  lista={meses}
                />
              </FormGroup>

              <FormGroup htmlFor="inputTipo" label="Tipo: *">
                <SelectMenu
                  value={this.state.tipo}
                  onChange={(e) => this.setState({ tipo: e.target.value })}
                  id="inputTipo"
                  className="form-control mb-3"
                  lista={tipos}
                />
              </FormGroup>

              <button
                onClick={this.buscar}
                type="button"
                className="btn btn-success mr-1"
              >
                Buscar
              </button>
              <button type="button" className="btn btn-danger">
                Cadastrar
              </button>
            </div>
          </div>
        </div>
        <br />
        <div className="row">
          <div className="col-md-12">
            <div className="bs-component">
              <LancamentosTable
                deleteAction={this.abrirConfirmacao}
                editAction={this.editar}
                lancamentos={this.state.lancamentos}
              />
            </div>
          </div>
        </div>
        <div>
          <Dialog
            header="Confirmação"
            visible={this.state.showConfirmDialog}
            style={{ width: "50vw" }}
            footer={confirmFooter}
            onHide={() => this.setState({showConfirmDialog: false})}
          >
            <p className="m-0">
              Confirma a exclusão deste lancamento?
            </p>
          </Dialog>
        </div>
      </Card>
    );
  }
}

export default ConsultaLacamentos;