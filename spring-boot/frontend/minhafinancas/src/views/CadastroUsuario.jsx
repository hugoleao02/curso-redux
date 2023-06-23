import React, { Component } from "react";
import { withRouter } from "react-router-dom";

import Card from "../components/Card";
import FormGroup from "../components/FormGroup";

class CadastroUsuario extends Component {
  state = {
    nome: "",
    email: "",
    senha: "",
    senhaRepeticao: "",
  };

  cadastrar = () => {
    console.log(this.state.nome);
    console.log(this.state.email);
    console.log(this.state.senha);
    console.log(this.state.senhaRepeticao);
  };

  cancelar =()=>{
    this.props.history.push('/login')
  }

  render() {
    return (
      <Card title="Cadastro de Usuário">
        <div className="row">
          <div className="col-lg-12">
            <div className="bs-component">
              <FormGroup label="Email: *" htmlFor="inputNome">
                <input
                  type="text"
                  className="form-control"
                  id="inputNome"
                  placeholder="Digite o Nome"
                  onChange={(e) => this.setState({ nome: e.target.value })}
                />
              </FormGroup>
              <FormGroup label="Nome: *" htmlFor="inputEmail">
                <input
                  type="email"
                  className="form-control"
                  id="inputEmail"
                  placeholder="Digite o E-mail"
                  onChange={(e) => this.setState({ email: e.target.value })}
                />
              </FormGroup>
              <FormGroup label="Repita a senha: *" htmlFor="inputRepitaSenha">
                <input
                  type="password"
                  className="form-control"
                  id="inputRepitaSenha"
                  placeholder="Digite o Senha"
                  onChange={(e) => this.setState({ senha: e.target.value })}
                />
              </FormGroup>
              <FormGroup label="Senha: *" htmlFor="inputSenha">
                <input
                  type="password"
                  className="form-control"
                  id="inputSenha"
                  placeholder="Digite novamente a Senha"
                  onChange={(e) => this.setState({ senha: e.target.value })}
                />
              </FormGroup>

              <button
                type="button"
                onClick={this.cadastrar}
                className="btn btn-success mr-1 mt-3"
              >
                Salvar
              </button>
              <button type="button" 
                onClick={this.cancelar}
                className="btn btn-danger mt-3">
                Voltar
              </button>
            </div>
          </div>
        </div>
      </Card>
    );
  }
}

export default withRouter(CadastroUsuario);
