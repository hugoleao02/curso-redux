import ApiService from "../apiService";

import ErroValidacao from "../exception/errosValidacao";

export default class LacamentoService extends ApiService {
  constructor() {
    super("/api/lancamentos");
  }

  obterListaMeses() {
    return [
      { label: "Selecione...", value: "" },
      { label: "Janeiro", value: 1 },
      { label: "Fevereiro", value: 2 },
      { label: "Março", value: 3 },
      { label: "Abril", value: 4 },
      { label: "Maio", value: 5 },
      { label: "Junho", value: 6 },
      { label: "Julho", value: 7 },
      { label: "Agosto", value: 8 },
      { label: "Setembro", value: 9 },
      { label: "Outubro", value: 10 },
      { label: "Novembro", value: 11 },
      { label: "Dezembro", value: 12 },
    ];
  }

  obterPorId(id) {
    return this.get(`/${id}`);
  }

  obterListasTipos() {
    return [
      { label: "Selecione...", value: "" },
      { label: "Despesa", value: "DESPESA" },
      { label: "Receita", value: "RECEITA" },
    ];
  }

  salvar(lancamento) {
    return this.post("", lancamento);
  }

  atualizar(lancamento) {
    return this.put(`/${lancamento.id}`, lancamento);
  }

  validar(lancamento) {
    const erros = [];

    if (!lancamento.ano) {
      erros.push("Informe o Ano.");
    }
    if (!lancamento.mes) {
      erros.push("Informe o mes.");
    }
    if (!lancamento.descricao) {
      erros.push("Informe o descricao.");
    }
    if (!lancamento.valor) {
      erros.push("Informe o valor.");
    }
    if (!lancamento.tipo) {
      erros.push("Informe o tipo.");
    }

    if (erros && erros.length > 0) {
      throw new ErroValidacao(erros);
    }
  }

  consulta(lacamentoFiltro) {
    let params = `?ano=${lacamentoFiltro.ano}`;

    if (lacamentoFiltro.mes) {
      params = `${params}&mes=${lacamentoFiltro.mes}`;
    }

    if (lacamentoFiltro.tipo) {
      params = `${params}&tipo=${lacamentoFiltro.tipo}`;
    }

    if (lacamentoFiltro.status) {
      params = `${params}&status=${lacamentoFiltro.status}`;
    }

    if (lacamentoFiltro.usuario) {
      params = `${params}&usuario=${lacamentoFiltro.usuario}`;
    }

    if (lacamentoFiltro.descricao) {
      params = `${params}&descricao=${lacamentoFiltro.descricao}`;
    }

    return this.get(params);
  }

  deletar(id) {
    return this.delete(`/${id}`);
  }

  alterarStatus(id, status) {
    return this.put(`/${id}/atualiza-status`, {status});
  }
}
