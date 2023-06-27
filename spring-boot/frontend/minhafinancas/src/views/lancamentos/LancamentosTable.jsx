import React from "react";
import currencyFormatter from "currency-formatter";

export default (props) => {
  const rows = props.lancamentos.map((lacamento) => {
    return (
      <tr key={lacamento.id}>
        <td>{lacamento.descricao}</td>
        <td>
          {currencyFormatter.format(lacamento.valor, {
            locale: "pt-BR",
          })}
        </td>
        <td>{lacamento.tipo}</td>
        <td>{lacamento.mes}</td>
        <td>{lacamento.status}</td>
        <td>
          <button
            type="button" title="Efetivar"
            disabled={lacamento.status !== 'PENDENTE'}
            className="btn btn-success btn-sm"
            onClick={(e) => props.alterarStatus(lacamento, "EFETIVADO")}
          >
           <i className="pi pi-check"></i>
          </button>

          <button
            type="button" title="Cancelar"
            disabled={lacamento.status !== 'PENDENTE'}
            className="btn btn-warning btn-sm"
            onClick={(e) => props.alterarStatus(lacamento, "CANCELADO")}
          >
            <i className="pi pi-times"></i>
          </button>

          <button
            type="button" title="Editar"
            className="btn btn-primary btn-sm"
            onClick={(e) => props.editAction(lacamento)}
          >
            <i className="pi pi-pencil"></i>
          </button>
          <button
            type="button" title="Deletar"
            className="btn btn-danger btn-sm"
            onClick={(e) => props.deleteAction(lacamento)}
          >
           <i className="pi pi-trash"></i>
          </button>
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
