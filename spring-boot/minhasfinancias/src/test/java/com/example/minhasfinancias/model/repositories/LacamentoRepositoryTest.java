package com.example.minhasfinancias.model.repositories;

import com.example.minhasfinancias.model.entity.StatusLancamento;
import com.example.minhasfinancias.model.entity.TipoLancamento;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import com.example.minhasfinancias.model.entity.Lancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class LacamentoRepositoryTest {
    
    @Autowired
    LancamentoRepository repository;
    
    @Autowired
    TestEntityManager entityManager;
    
    @Test
    public void deveSalvarUmLancamento(){
        Lancamento lacamento = criarLancamento();
        
        lacamento = repository.save(lacamento);
        
      assertNotNull(lacamento.getId());
    }
    
    @Test
    public  void deveDeletarUmLancamento(){
        Lancamento lancamento = criarEPersistirLancamento();
        
        lancamento = entityManager.find(Lancamento.class, lancamento.getId());
        repository.delete(lancamento);
        
        Lancamento lancamenteInexistente = entityManager.find(Lancamento.class, lancamento.getId());
        assertNull(lancamenteInexistente);
    }
    
    
    @Test
    public  void  deveAtualizarUmLacamento(){
        Lancamento lancamento = criarEPersistirLancamento();
        
        lancamento.setAno(2018);
        lancamento.setDescricao("Teste Atualizar");
        lancamento.setStatus(StatusLancamento.CANCELADO);
        repository.save(lancamento);
        
        Lancamento lancamentoAtualizado = entityManager.find(Lancamento.class, lancamento.getId());
        assertEquals(2018, lancamentoAtualizado.getAno());
        assertEquals("Teste Atualizar", lancamentoAtualizado.getDescricao());
        assertEquals(StatusLancamento.CANCELADO, lancamentoAtualizado.getStatus());
    }
    
    @Test
    public void deveBuscarUmLacamentoPorID(){
        Lancamento lancamento = criarEPersistirLancamento();
        
        Optional<Lancamento> lancamentoEncontrado = repository.findById(lancamento.getId());
        
        assertTrue(lancamentoEncontrado.isPresent());
    }
    
    
    private Lancamento criarEPersistirLancamento() {
        Lancamento lancamento = criarLancamento();
        entityManager.persist(lancamento);
        return lancamento;
    }
    
    
    public static Lancamento criarLancamento(){
        return Lancamento.builder()
                .ano(2019)
                .mes(1)
                .descricao("lancamento qualquer")
                .valor(BigDecimal.valueOf(10))
                .tipo(TipoLancamento.RECEITA)
                .status(StatusLancamento.PENDENTE)
                .dataCadastro(LocalDate.now())
                .build();
    }
    
    
}
