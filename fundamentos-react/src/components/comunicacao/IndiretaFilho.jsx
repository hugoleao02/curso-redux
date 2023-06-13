import React from "react";

export default props => {

    const gerarIdade = () => Math.floor(Math.random() * (20)) + 50;
    const gerarNerd = () => Math.random() > 0.5;
    const quandoClicar = () => props.quandoClicar('João',gerarIdade(), gerarNerd());
    return (
        <div>
            <div> Filho </div>
            <button
                onClick={quandoClicar}
            >Fornecer Informaçoes</button>
        </div>
    )
}