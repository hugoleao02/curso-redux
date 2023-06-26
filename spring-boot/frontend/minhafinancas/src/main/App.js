import "bootswatch/dist/flatly/bootstrap.css";
import "toastr/build/toastr.css";
import "./custom.css";

//theme
import "primereact/resources/themes/lara-light-indigo/theme.css";     
    
//core
import "primereact/resources/primereact.min.css";      

import "toastr/build/toastr.min.js";

import React, { Component } from "react";

import Rotas from "./Rotas";
import Navbar from "../components/Navbar";

class App extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <div className="container">
          <Rotas />
        </div>
      </div>
    );
  }
}

export default App;
