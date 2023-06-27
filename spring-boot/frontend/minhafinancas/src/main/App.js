import "bootswatch/dist/flatly/bootstrap.css";
import "toastr/build/toastr.css";
import "./custom.css";

import "primereact/resources/themes/lara-light-indigo/theme.css";
import "primeicons/primeicons.css";
import "primereact/resources/primereact.min.css";

import "toastr/build/toastr.min.js";

import React, { Component } from "react";

import Rotas from "./Rotas";
import Navbar from "../components/Navbar";
import ProvedorAutenticacao from "./ProvedorAutenticacao";

class App extends Component {
  render() {
    return (
      <ProvedorAutenticacao>
        <Navbar />
        <div className="container">
          <Rotas />
        </div>
      </ProvedorAutenticacao>
    );
  }
}

export default App;
