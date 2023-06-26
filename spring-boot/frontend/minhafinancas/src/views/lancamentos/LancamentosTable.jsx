import React from "react";
import currencyFormatter from "currency-formatter";

export default (props) => {
  const rows = props.lancamentos.map((lacamento) => {
    return (
      <tr key={lacamento.id}>
        <td>{lacamento.descricao}</td>
        <td>{currencyFormatter.format(lacamento.valor, {
            locale:'pt-BR'
        })}</td>
        <td>{lacamento.tipo}</td>
        <td>{lacamento.mes}</td>
        <td>{lacamento.status}</td>
        <td>
        <button 
        ype="button" 
        className="btn btn-primary"
        onClick={e => props.editAction(lacamento)}
        >Editar</button>
        <button 
        type="button"
        className="btn btn-danger" 
        onClick={e => props.deleteAction(lacamento)}>Deletar</button>
        </td>
      </tr>
    );
  });

  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">Descrição</th>
          <th scope="col">Valor</th>
          <th scope="col">Tipo</th>
          <th scope="col">Data</th>
          <th scope="col">Situação</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>{rows}</tbody>
    </table>
  );
};
