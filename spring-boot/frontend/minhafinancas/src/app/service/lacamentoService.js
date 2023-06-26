import ApiService from "../apiService";

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

  obterListasTipos() {
    return [
      { label: "Selecione...", value: "" },
      { label: "Despesa", value: "DESPESA" },
      { label: "Receita", value: "RECEITA" },
    ];
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

  deletar(id){
    return this.delete(`/${id}`)
  }
}
