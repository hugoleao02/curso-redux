import React, { Component } from "react";

import { withRouter } from "react-router-dom";

import Card from "../../components/Card";
import FormGroup from "../../components/FormGroup";
import SelectMenu from "../../components/SelectMenu";

import LacamentoService from "../../app/service/lacamentoService";

class CadastroLancamentos extends Component {
  state = {
    id: null,
    descricao: "",
    valor: "",
    ano: "",
    mes: "",
    tipo: "",
    status: "",
  };

  submit = () => {
    console.log(this.state);
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
      <Card title="Cadastro de Lançamentos">
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
            <button onClick={this.submit} className="btn btn-success mr-1 ">
              Salvar
            </button>
            <button className="btn btn-danger">Cancelar</button>
          </div>
        </div>
      </Card>
    );
  }
}

export default withRouter(CadastroLancamentos);
