import './Mega.css'
import React, { useState } from "react";


export default props => {
    function gerarNumeroNaoContido(min, max, array) {
        const aleatorio = parseInt(Math.random() * (max + 1 - min)) + min;
        return array.includes(aleatorio) ? gerarNumeroNaoContido(min, max, array) : aleatorio;
    }

    const [qtd, setQtd] = useState(props.qtd || 6);
    const numeroIniciais = Array(qtd).fill(0);
    const [numeros, setNumeros] = useState(numeroIniciais);

    function geraNumeros(qtd) {
        const numeros = Array(qtd)
            .fill(0)
            .reduce((nums) => {
                const novoNumero = gerarNumeroNaoContido(1, 60, nums);
                return [...nums, novoNumero];
            }, [])
            .sort((n1, n2) => n1 - n2);

        return numeros;
    }

    return (
        <div className='Mega'>
            <h2>Mega</h2>
            <h3>{numeros.join(' ')}</h3>
            <div>
                <label>Quantidades de Números </label>
                <input
                    type="number"
                    value={qtd}
                    onChange={e => setQtd(+e.target.value)}
                    min="6"
                    max="15"

                />
            </div>
            <button onClick={() => setNumeros(geraNumeros(qtd))}>Gerar Numeros</button>
        </div>
    )
}