package com.example.minhasfinancias.model.repositories;

import com.example.minhasfinancias.model.entity.Usuario;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    
    @Autowired
    UsuarioRepository repository;
    
    @Autowired
    TestEntityManager entityManager;
    
    @Test
    public void deveVerificarAExistenciaDeUmEmail(){
       Usuario usuario = criarUsuario();
       entityManager.persist(usuario);
        
        boolean result = repository.existsByEmail("usuario@email.com");
        
        Assertions.assertTrue(result);
    }
    
    
    @Test
    public void deveRetornaFalsoQuandoNaoHouberUsuarioCadastradoComOEmail(){
        boolean result = repository.existsByEmail("usuario@email.com");
        
        Assertions.assertFalse(result);
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados(){
        Usuario usuario = criarUsuario();
        
        Usuario usuarioSalvo = repository.save(usuario);
        
        Assertions.assertNotNull(usuarioSalvo.getId());
        
    }
    
    @Test
    public void deveBuscarUmUsuarioPorEmail(){
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);
        
       Optional<Usuario> result = repository.findByEmail("email@email.com");
       
       Assertions.assertTrue(result.isPresent());
        
    }
    
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase(){
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);
        
        Optional<Usuario> result = repository.findByEmail("email@email.com");
        
        Assertions.assertFalse(result.isPresent());
        
    }
    
    
    public  static Usuario criarUsuario(){
        return Usuario
                .builder()
                .nome("usuario")
                .senha("123")
                .email("email@email.com")
                .build();
    }
  
    
    
}
