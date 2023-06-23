package com.example.minhasfinancias.services;

import com.example.minhasfinancias.exception.RegraNegocioException;
import com.example.minhasfinancias.model.entity.Lancamento;
import com.example.minhasfinancias.model.entity.StatusLancamento;
import com.example.minhasfinancias.model.entity.Usuario;
import com.example.minhasfinancias.model.repositories.LacamentoRepositoryTest;
import com.example.minhasfinancias.model.repositories.LancamentoRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.minhasfinancias.services.impl.LacamentoServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LancamentoServiceTest {
    
    @SpyBean
    LacamentoServiceImpl service;
    @MockBean
    LancamentoRepository repository;
    
    
    @Test
    public void deveSalvarUmLancamento(){
        Lancamento lancamentoSalvar = LacamentoRepositoryTest.criarLancamento();
        Mockito.doNothing().when(service).validar(lancamentoSalvar);
        Mockito.when(repository.save(lancamentoSalvar)).thenReturn(lancamentoSalvar);
        
        Lancamento lancamentoSalvo = LacamentoRepositoryTest.criarLancamento();
        lancamentoSalvo.setId(1l);
        lancamentoSalvo.setStatus(StatusLancamento.PENDENTE);
        Mockito.when(repository.save(lancamentoSalvar)).thenReturn(lancamentoSalvo);
        
        Lancamento lancamento = service.salvar(lancamentoSalvar);
        
        Assertions.assertEquals(lancamentoSalvo.getId(), lancamento.getId());
        Assertions.assertEquals(StatusLancamento.PENDENTE, lancamento.getStatus());
    }
 
    @Test
    public void naoDeveSalvarUmlancamentoQuandoHouverErroDeValidacao(){
        Lancamento lancamentoSalvar = LacamentoRepositoryTest.criarLancamento();
        Mockito.doThrow(RegraNegocioException.class).when(service).validar(lancamentoSalvar);
        
        Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.salvar(lancamentoSalvar);
        });
        
        Mockito.verify(repository, Mockito.never()).save(lancamentoSalvar);
    }
    
    @Test
    public void deveAtualizarUmLancamento(){
        
        Lancamento lancamentoSalvo = LacamentoRepositoryTest.criarLancamento();
        lancamentoSalvo.setId(1l);
        lancamentoSalvo.setStatus(StatusLancamento.PENDENTE);
        
        Mockito.doNothing().when(service).validar(lancamentoSalvo);
        
        Mockito.when(repository.save(lancamentoSalvo)).thenReturn(lancamentoSalvo);
        
        service.atualizar(lancamentoSalvo);
        Mockito.verify(repository, Mockito.times(1)).save(lancamentoSalvo);
    }
    
    @Test
    public void deveLancarErroAoTentarAtualizarUmLancamentoQueAindaNaoFoiSalvo(){
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            service.atualizar(lancamento);
        });
        
        Mockito.verify(repository, Mockito.never()).save(lancamento);
    }
    
    @Test
    public void deveDeletarUmLancamento(){
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        lancamento.setId(1l);
        
        service.deletar(lancamento);
        
        Mockito.verify(repository).delete(lancamento);
    }
    
    @Test
    public void deveLancarErroAoTentarDeletarUmLancamentoQueAindaNaoFoiSalvo(){
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        
       
        Assertions.assertThrows(NullPointerException.class, () -> {
            service.deletar(lancamento);
        });
        
        Mockito.verify(repository, Mockito.never()).delete(lancamento);
    }
    
    @Test
    public void deveFiltraLancamento(){
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        lancamento.setId(1l);
        
        List<Lancamento> lista = Arrays.asList(lancamento);
        Mockito.when((repository.findAll(Mockito.any(Example.class)))).thenReturn(lista);
        
        List<Lancamento> resultado = service.buscar(lancamento);
        
        Assertions.assertAll(
                () -> Assertions.assertFalse(resultado.isEmpty()),
                () -> Assertions.assertEquals(1, resultado.size()),
                () -> Assertions.assertTrue(resultado.contains(lancamento))
        );
    }
    
    @Test
    public  void deveAtualizarOsStatusDeUmLancamento(){
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        lancamento.setId(1l);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        
        StatusLancamento novoStatus = StatusLancamento.EFETIVADO;
        Mockito.doReturn(lancamento).when(service).atualizar(lancamento);
        
        service.atualizarStatus(lancamento, novoStatus);
        
        Assertions.assertEquals(novoStatus, lancamento.getStatus());
        Mockito.verify(service).atualizar(lancamento);
    }
    
    @Test
    public void deveObterUmLancamentoPorID(){
        Long id = 1l;
        
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        lancamento.setId(id);
        
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(lancamento));
        
        Optional<Lancamento> resultado = service.obterPorId(id);
        
        Assertions.assertTrue(resultado.isPresent());
    }
    
    @Test
    public void deveRetornarVazioQuandoLancamentoNaoExistir(){
        Long id = 1l;
        
        Lancamento lancamento = LacamentoRepositoryTest.criarLancamento();
        lancamento.setId(id);
        
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        
        Optional<Lancamento> resultado = service.obterPorId(id);
        
        Assertions.assertFalse(resultado.isPresent());
    }
    
    
    @Test
    public void deveLancarErrosAoValidarUmLancamento() {
        
        // cenário
        Lancamento lancamento = new Lancamento();
        
        Throwable erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe uma descrição válida."));
        
        lancamento.setDescricao("");
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe uma descrição válida."));
        
        lancamento.setDescricao("Salario");
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Mês válido."));
        
        lancamento.setAno(0);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Mês válido."));
        
        lancamento.setAno(13);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Mês válido."));
        
        lancamento.setMes(1);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Ano válido."));
        
        lancamento.setAno(202);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Ano válido."));
        
        lancamento.setAno(2020);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Usuário."));
        
        lancamento.setUsuario(new Usuario());
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Usuário."));
        
        lancamento.getUsuario().setId(1L);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Valor válido."));
        
        lancamento.setValor(BigDecimal.ZERO);
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um Valor válido."));
        
        lancamento.setValor(BigDecimal.valueOf(1));
        
        erro = Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(lancamento));
        Assertions.assertTrue(erro.getMessage().equals("Informe um tipo de Lançamento."));
    }
    
    
    
    
}
