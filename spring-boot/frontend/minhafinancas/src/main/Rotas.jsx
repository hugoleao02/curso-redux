import React from "react";

import Login from '../views/Login';
import CadastroUsuario from '../views/CadastroUsuario';
import  Home from '../views/Home';    

import {BrowserRouter, Switch, Route} from 'react-router-dom';



function Rotas(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/home" component={Home} />
                <Route path="/login" component={Login} />
                <Route path="/cadastro-usuarios" component={CadastroUsuario} />
            </Switch>
        </BrowserRouter>
    )
}

export default Rotas;