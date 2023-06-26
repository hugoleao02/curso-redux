import React, { Component } from "react";
import { withRouter } from "react-router-dom";

import UsuarioService from "../app/service/usuarioService";
import LocalStorageService from "../app/service/localStoregeService";

import Card from "../components/Card";
import FormGroup from "../components/FormGroup";
import {mensagemErro} from "../components/toastr";
 

class Login extends Component {
  state = {
    email: "",
    senha: ""
  };

  constructor() {
    super();
    this.service = new UsuarioService();
  }

  entrar = () => {
    this.service
      .autenticar({
        email: this.state.email,
        senha: this.state.senha,
      })
      .then((response) => {
        LocalStorageService.adicionarItem("_usuario_logado", response.data);
        this.props.history.push("/home");
      })
      .catch((erro) => {
        mensagemErro(erro.response.data);
      });
  };

  prepareCadastrar = () => {
    this.props.history.push("/cadastro-usuarios");
  };

  render() {
    return (
      <div className="row">
        <div
          className="col-md-6"
          style={{ position: "relative", left: "300px" }}
        >
          <div className="bs-docs-section">
            <Card title="Login">
              <div className="row">
                <div className="col-lg-12">
                  <div className="bs-component">
                    <fieldset>
                      <FormGroup label="Email: *" htmlFor="exempleInputEmail1">
                        <input
                          value={this.state.email}
                          onChange={(e) =>
                            this.setState({ email: e.target.value })
                          }
                          type="email"
                          className="form-control"
                          id="exampleInputEmail1"
                          aria-describedby="emailHelp"
                          placeholder="Digite o Email"
                        />
                      </FormGroup>

                      <FormGroup
                        title="Senha: *"
                        htmlFor="exampleInputPassword1"
                      >
                        <input
                          value={this.state.senha}
                          onChange={(e) =>
                            this.setState({ senha: e.target.value })
                          }
                          type="password"
                          className="form-control mb-3"
                          id="exampleInputPassword1"
                          placeholder="Password"
                        />
                      </FormGroup>

                      <button
                        onClick={this.entrar}
                        type="button"
                        className="btn btn-success mr-1"
                      >
                        Entrar
                      </button>

                      <button
                        onClick={this.prepareCadastrar}
                        type="button"
                        className="btn btn-danger "
                      >
                        Cadastrar
                      </button>
                    </fieldset>
                  </div>
                </div>
              </div>
            </Card>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(Login);
