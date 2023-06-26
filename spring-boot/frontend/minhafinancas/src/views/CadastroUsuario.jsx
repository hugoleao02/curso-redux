import React, { Component } from "react";
import { withRouter } from "react-router-dom";

import { mensagemErro, mensagemSucesso } from "../components/toastr";

import Card from "../components/Card";
import FormGroup from "../components/FormGroup";
import UsuarioService from "../app/service/usuarioService";

class CadastroUsuario extends Component {
  state = {
    nome: "",
    email: "",
    senha: "",
    senhaRepeticao: "",
  };

  constructor() {
    super();
    this.service = new UsuarioService();
  }

  validar() {
    const msgs = [];
    if (!this.state.nome) {
      msgs.push("O campo Nome é obrigatório.");
    }
    if (!this.state.email) {
      msgs.push("O campo Email é obrigatório.");
    } else if (!this.state.email.match(/^[a-z0-9.]+@[a-z0-9]+\.[a-z]/)) {
      msgs.push("Informe um Email válido.");
    }

    if (!this.state.senha || !this.state.senhaRepeticao) {
      msgs.push("Digite a senha 2x.");
    } else if (this.state.senha !== this.state.senhaRepeticao) {
      msgs.push("As senhas não batem.");
    }

    return msgs;
  }

  cadastrar = () => {
    const msgs = this.validar();

    if (msgs && msgs.length > 0) {
      msgs.forEach((msg) => {
        mensagemErro(msg);
      });
      return false;
    }

    const usuario = {
      nome: this.state.nome,
      email: this.state.email,
      senha: this.state.senha,
    };
    this.service
      .salvar(usuario)
      .then((response) => {
        mensagemSucesso(
          "Usuário cadastrado com sucesso! Faça o login para acessa o sistema."
        );
        this.props.history.push("/login");
      })
      .catch((error) => {
        mensagemErro(error.response.data);
      });
  };

  cancelar = () => {
    this.props.history.push("/login");
  };

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
                  onChange={(e) => this.setState({ senhaRepeticao: e.target.value })}
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
              <button
                type="button"
                onClick={this.cancelar}
                className="btn btn-danger mt-3"
              >
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
