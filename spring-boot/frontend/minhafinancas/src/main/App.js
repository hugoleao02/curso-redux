import "bootswatch/dist/flatly/bootstrap.css";
import "./custom.css";
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
