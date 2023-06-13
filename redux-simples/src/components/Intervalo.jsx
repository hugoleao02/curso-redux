import { connect } from 'react-redux';
import Card from './Card';
import './Intervalo.css';
import React from 'react';

import { alteraNumeroMinimo, alteraNumeroMaximo } from '../store/actions/numeros';

function Intervalo(props) {
    const {min, max} = props;
    
    
    return (
        <Card Title="Intervalo de número" red>
            <div className='Intervalo'>
                <span>
                    <strong>Mínimo: </strong>
                    <input type="number" value={min} onChange={e => props.alterarMinimo(+e.target.value)} />
                </span>
                <span>
                    <strong>Máximo: </strong>
                    <input type="number" value={max} onChange={e => props.alteraMaximo(+e.target.value)} />
                </span>
            </div>
        </Card>
    )
}


function mapStateToProps(state){
    return {
     min: state.numeros.min,
     max: state.numeros.max
    }
  }

  function mapDispatchToProp(dispatch){
    return {
        alterarMinimo(novoNumero){
           const action = alteraNumeroMinimo(novoNumero);
           dispatch(action);
        },
        alteraMaximo(novoNumero){
            const action = alteraNumeroMaximo(novoNumero);
            dispatch(action);
         }
    }
  }

  
  export default connect(
    mapStateToProps,
    mapDispatchToProp
    )(Intervalo);