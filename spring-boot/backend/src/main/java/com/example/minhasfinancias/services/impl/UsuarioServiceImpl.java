package com.example.minhasfinancias.services.impl;

import com.example.minhasfinancias.exception.ErroAutenticacao;
import com.example.minhasfinancias.exception.RegraNegocioException;
import com.example.minhasfinancias.model.entity.Usuario;
import com.example.minhasfinancias.model.repositories.UsuarioRepository;
import com.example.minhasfinancias.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private UsuarioRepository repository;
 
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }
    
    
    @Override
    public Usuario autenticar(String email, String senha) {
       Optional<Usuario> usuario =  repository.findByEmail(email);
       
       if(usuario.isEmpty()){
           throw new ErroAutenticacao("Usuário não encontrado.");
       }
       
       if(!usuario.get().getSenha().equals(senha)){
           throw new ErroAutenticacao("Senha Inválida.");
       }
       
        return usuario.get();
    }
    
    @Override
    @Transactional
    public Usuario salvaUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }
    
    @Override
    public void validarEmail(String email) {
       Optional<Usuario> existe = repository.findByEmail(email);
       
       if (existe.isPresent()){
           throw  new RegraNegocioException("Já existe um usuário cadastrado com este email.");
       }
    }
    
    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return repository.findById(id);
    }
}
