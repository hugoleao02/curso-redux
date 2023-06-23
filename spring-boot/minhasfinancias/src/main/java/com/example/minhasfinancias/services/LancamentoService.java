package com.example.minhasfinancias.services;

import com.example.minhasfinancias.model.entity.Lancamento;
import com.example.minhasfinancias.model.entity.StatusLancamento;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LancamentoService {
    
    Lancamento salvar(Lancamento lancamento);

    @Transactional
    Lancamento atualizar(Lancamento lancamento);
    
    void deletar (Lancamento lancamento);
    List<Lancamento> buscar(Lancamento lancamentoFiltro);
    void  atualizarStatus(Lancamento lancamento, StatusLancamento status);
    void validar(Lancamento lancamento);
    
    Optional<Lancamento> obterPorId(Long id);
    
    BigDecimal obterSaldoPorUsuario(Long id);
}
