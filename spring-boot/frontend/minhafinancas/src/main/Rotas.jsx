import React from "react";

import  Home from '../views/Home';    
import Login from '../views/Login';
import CadastroUsuario from '../views/CadastroUsuario';
import ConsultaLacamentos from "../views/lancamentos/ConsultaLancamentos";
import CadastroLancamento from "../views/lancamentos/CadastroLancamento";

import {BrowserRouter, Switch, Route} from 'react-router-dom';



function Rotas(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/home" component={Home} />
                <Route path="/login" component={Login} />
                <Route path="/cadastro-usuarios" component={CadastroUsuario} />
                <Route path="/consulta-lancamentos" component={ConsultaLacamentos} />
                <Route path="/cadastro-lancamentos" component={CadastroLancamento} />
            </Switch>
        </BrowserRouter>
    )
}

export default Rotas;