import React from "react";


export default (props) => {
    const max = props.max;
    const min = props.min;
    const aleatorio = Math.floor(parseInt(Math.random() * (max - min))) + min;
    return (
        <div>
            <h2>Valor aleatório</h2>
            <p><strong>Valor Mínimo: </strong> {min}</p>
            <p><strong>Valor Máximo: </strong> {max}</p>
            <p><strong>Valor Escolhido: </strong> {aleatorio}</p>
        </div>
    )
}