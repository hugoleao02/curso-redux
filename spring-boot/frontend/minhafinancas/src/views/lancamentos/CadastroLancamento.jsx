import React, { Component } from "react";

import { withRouter } from "react-router-dom";
import * as messages from "../../components/toastr";

import Card from "../../components/Card";
import FormGroup from "../../components/FormGroup";
import SelectMenu from "../../components/SelectMenu";

import LacamentoService from "../../app/service/lacamentoService";
import LocalStoregeService from "../../app/service/localStoregeService";

class CadastroLancamentos extends Component {
  state = {
    id: null,
    descricao: "",
    valor: "",
    ano: "",
    mes: "",
    tipo: "",
    status: "",
    usuario: null,
    atualizando: false,
  };

  componentDidMount() {
    const params = this.props.match.params;

    if (params.id) {
      this.service
        .obterPorId(params.id)
        .then((response) => {
          this.setState({ ...response.data, atualizando: true });
        })
        .catch((erros) => {
          messages.mensagemErro(erros.response.data);
        });
    }
  }

  atualizar = () => {
    const { descricao, valor, mes, ano, tipo, id, usuario } = this.state;
    const lancamento = {
      descricao,
      valor,
      mes,
      ano,
      tipo,
      id,
      usuario,
    };

    this.service.salvar(lancamento);
    this.props.history
      .push("/consulta-lancamentos")
      .then((response) => {
        messages.mensagemSucesso("Lançamento atualizado  com sucesso!");
      })
      .catch((error) => {
        messages.mensagemErro(error.response.data);
      });
  };

  submit = () => {
    const usuarioLogado = LocalStoregeService.obterItem("_usuario_logado");
  
    const { descricao, valor, mes, ano, tipo } = this.state;
    const lancamento = {
      descricao,
      valor,
      mes,
      ano,
      tipo,
      usuario: usuarioLogado.id,
    };
  
    try {
      this.service.validar(lancamento);
    } catch (error) {
      const mensagens = error.mensagens;
      mensagens.forEach((msg) => {
        messages.mensagemErro(msg);
      });
      return false;
    }
  
    this.service.salvar(lancamento);
    this.props.history.push("/consulta-lancamentos");
    messages.mensagemSucesso("Lançamento cadastrado com sucesso!");
  };
  
   
  handleChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;

    this.setState({ [name]: value });
  };

  constructor() {
    super();
    this.service = new LacamentoService();
  }

  render() {
    const tipos = this.service.obterListasTipos();
    const meses = this.service.obterListaMeses();

    return (
      <Card
        title={
          this.state.atualizando
            ? "Atualizando de lançamento"
            : "Cadastro de lançamento"
        }
      >
        <div className="row">
          <div className="col-md-12">
            <FormGroup id="inputDescricao" label="Descrição: *">
              <input
                id="inputDescricao"
                type="text"
                className="form-control"
                name="descricao"
                value={this.state.descricao}
                onChange={this.handleChange}
              />
            </FormGroup>
          </div>
        </div>
        <div className="row">
          <div className="col-md-6">
            <FormGroup id="inputAno" label="Ano: *">
              <input
                id="inputAno"
                type="text"
                name="ano"
                value={this.state.ano}
                onChange={this.handleChange}
                className="form-control"
              />
            </FormGroup>
          </div>
          <div className="col-md-6">
            <FormGroup id="inputMes" label="Mês: *">
              <SelectMenu
                name="mes"
                value={this.state.mes}
                onChange={this.handleChange}
                id="inputMes"
                lista={meses}
                className="form-control"
              />
            </FormGroup>
          </div>
        </div>

        <div className="row">
          <div className="col-md-4">
            <FormGroup id="inputValor" label="Valor: *">
              <input
                id="inputValor"
                type="text"
                name="valor"
                value={this.state.valor}
                onChange={this.handleChange}
                className="form-control"
              />
            </FormGroup>
          </div>

          <div className="col-md-4">
            <FormGroup id="inputTipo" label="Tipo: *">
              <SelectMenu
                name="tipo"
                value={this.state.tipo}
                onChange={this.handleChange}
                id="inputTipo"
                lista={tipos}
                className="form-control"
              />
            </FormGroup>
          </div>

          <div className="col-md-4">
            <FormGroup id="inputStatus" label="Status: ">
              <input
                type="text"
                name="status"
                value={this.state.status}
                className="form-control"
                disabled
              />
            </FormGroup>
          </div>
        </div>

        <div className="row mt-3">
          <div className="col-md-6">
            {this.state.atualizando ? (
              <button
                onClick={this.atualizar}
                className="btn btn-primary mr-1 "
              >
                <i className="pi pi-refresh mr-1"> </i> 
                Atualizar
              </button>
            ) : (
              <button onClick={this.submit} className="btn btn-success mr-1 ">
                <i className="pi pi-save mr-1"> </i> 
                Salvar
              </button>
            )}

            <button
              onClick={(e) => this.props.history.push("/consulta-lancamentos")}
              className="btn btn-danger"
            >
              <i className="pi pi-times mr-1 "> </i> 
              Cancelar
            </button>
          </div>
        </div>
      </Card>
    );
  }
}

export default withRouter(CadastroLancamentos);
