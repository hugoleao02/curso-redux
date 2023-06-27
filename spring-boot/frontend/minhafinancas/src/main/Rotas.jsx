import React from "react";

import Home from "../views/Home";
import Login from "../views/Login";
import CadastroUsuario from "../views/CadastroUsuario";
import ConsultaLacamentos from "../views/lancamentos/ConsultaLancamentos";
import CadastroLancamentos from "../views/lancamentos/CadastroLancamento";

import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import { AuthConsumer } from "./ProvedorAutenticacao";

function RotaAutenticada({
  component: Component,
  isUsuarioAutenticado,
  ...props
}) {
  return (
    <Route
      {...props}
      render={(componentProps) => {
        if (isUsuarioAutenticado) {
          return <Component {...componentProps} />;
        } else {
          return (
            <Redirect
              to={{
                pathname: "/login",
                state: { from: componentProps.location },
              }}
            />
          );
        }
      }}
    />
  );
}

function Rotas(props) {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/cadastro-usuarios" component={CadastroUsuario} />

        <RotaAutenticada
          isUsuarioAutenticado={props.isUsuarioAutenticado}
          path="/home"
          component={Home}
        />
        <RotaAutenticada
          isUsuarioAutenticado={props.isUsuarioAutenticado}
          path="/consulta-lancamentos"
          component={ConsultaLacamentos}
        />
        <RotaAutenticada
          isUsuarioAutenticado={props.isUsuarioAutenticado}
          path="/cadastro-lancamentos/:id?"
          component={CadastroLancamentos}
        />
      </Switch>
    </BrowserRouter>
  );
}

export default () => (
  <AuthConsumer>
    {(context) => <Rotas isUsuarioAutenticado={context.isAutenticado}/>}
  </AuthConsumer>
);
