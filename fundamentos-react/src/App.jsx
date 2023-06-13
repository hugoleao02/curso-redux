import React from "react";
import './App.css';

import FamiliaMembro from "./components/basicos/FamiliaMembro";
import Primeiro from './components/basicos/Primeiro';
import ComParametro from './components/basicos/ComParametro';
import Fragmento from './components/basicos/Fragmento';
import Aleatorio from "./components/basicos/Aleatorio";
import Card from "./components/layout/Card";
import Familia from "./components/basicos/Familia";
import ListaAluno from "./components/repeticao/ListaAluno";
import TabelaProdutos from "./components/repeticao/TabelaProdutos";
import ParOuImpar from "./components/condicional/ParOuImpar";
import UsuarioInfo from "./components/condicional/UsuarioInfo";
import DiretaPai from "./components/comunicacao/DiretaPai";
import IndiretaPai from "./components/comunicacao/IndiretaPai";
import Input from "./components/formulario/Input";
import Contador from "./components/contador/Contador";
import Mega from "./components/mega/mega";

export default () =>
    <div className='App'>
        <h1>Fundamentos React</h1>

        <div className="Cards">

        <Card titulo="#13 - Mega" color="#B9006E">
               <Mega qtd={8}/>
            </Card>

            <Card titulo="#12 - Contador" color="#424242">
                <Contador numeroInicial={10} />
            </Card>

            <Card titulo="#11 -Componente Controlado (input)" color="#A52A2A">
                <Input />
            </Card>

            <Card titulo="#10 - Comunicação Indireta" color="#8BAD39">
                <IndiretaPai />
            </Card>

            <Card titulo="#09 - Comunicação Direta" color="#59323C">
                <DiretaPai />
            </Card>

            <Card titulo="#08 - Renderização condicional" color="#982395">
                <ParOuImpar numero={20} />
                <UsuarioInfo usuario={{ nome: "Fernando" }} />
                <UsuarioInfo usuario={{}} />
            </Card>


            <Card titulo="#07 - Desafio Repetição" color="#3A9AD9">
                <TabelaProdutos />
            </Card>

            <Card titulo="#06 - Repetição" color="#FF4C65">
                <ListaAluno ></ListaAluno>
            </Card>

            <Card titulo="#05 - Componentes Filhos" color="#00C8F8">
                <Familia sobrenome="Silva">
                    <FamiliaMembro nome="Pedro" />
                    <FamiliaMembro nome="Ana" />
                    <FamiliaMembro nome="Gustavo" />
                </Familia>
            </Card>

            <Card titulo="#04 - Desafio Aleatório" color="#FA6900">
                <Aleatorio min={10} max={60} />
            </Card>

            <Card titulo="#03 - Fragmento" color="#E94C6F">
                <Fragmento />
            </Card>

            <Card titulo="#02 - Com Parâmetro" color="#E8B71A">
                <ComParametro
                    titulo="Situação do aluno"
                    aluno="Pedro"
                    nota={9.3}
                />
            </Card>

            <Card titulo="#01 - Primeiro" color="#588C73">
                <Primeiro />
            </Card>
        </div>


    </div>

