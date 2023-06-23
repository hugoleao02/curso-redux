package com.example.minhasfinancias.services;

import com.example.minhasfinancias.exception.ErroAutenticacao;
import com.example.minhasfinancias.exception.RegraNegocioException;
import com.example.minhasfinancias.model.entity.Usuario;
import com.example.minhasfinancias.model.repositories.UsuarioRepository;
import com.example.minhasfinancias.services.impl.UsuarioServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
    
    @SpyBean
    UsuarioServiceImpl service;
    @MockBean
    UsuarioRepository repository;
    
    
    @Test(expected = Test.None.class)
    public void deveSalvarUmUsuario() {
        Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder()
                .id(1l)
                .nome("nome")
                .email("email@email.com")
                .senha("senha").build();
        
        Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        
        Usuario usuarioSalvo = service.salvaUsuario(new Usuario());
        
        Assertions.assertNotNull(usuarioSalvo);
        Assertions.assertEquals(1l, usuarioSalvo.getId());
        Assertions.assertEquals("nome", usuarioSalvo.getNome());
        Assertions.assertEquals("email@email.com", usuarioSalvo.getEmail());
        Assertions.assertEquals("senha", usuarioSalvo.getSenha());
    }
    
    @Test(expected = RegraNegocioException.class)
    public void naoDeveSalvarUmUsuarioComEmailCadastrado(){
        String email = "email@email.com";
        Usuario usuario = Usuario.builder().email(email).build();
        Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);
        
        service.salvaUsuario(usuario);
        
        Mockito.verify(repository, Mockito.never()).save(usuario);
    }
    
    
    @Test(expected = Test.None.class)
    public void deveValidarEmail() {
        
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
        
        service.validarEmail("email@email.com");
    }
    
    @Test(expected = Test.None.class)
    public void deveAutenticarUmUsuarioComSucesso() {
        String email = "email@email.com";
        String senha = "senha";
        
        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
        
        Usuario result = service.autenticar(email, senha);
        
        Assertions.assertNotNull(result);
    }
    
    @Test
    public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        
        
        Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() ->
                service.autenticar("email@email.com", "senha"));
        
        org.assertj.core.api.Assertions.assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class)
                .hasMessage("Usuário não encontrado.");
    }
    
    @Test
    public void deveLancarErroQuandoSenhaNaoBater() {
        String senha = "senha";
        Usuario usuario = Usuario.builder().senha("email@email.com").senha(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
        
        
        Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() ->
                service.autenticar("email@example.com", "123"));
        
        org.assertj.core.api.Assertions.assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class).hasMessage("Senha Inválida.");
        
        
    }
    
    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
        
        
        service.validarEmail("email@email.com");
        
    }
}
