package com.example.minhasfinancias.services;

import com.example.minhasfinancias.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {
    
    Usuario autenticar(String email, String senha);
    
    Usuario salvaUsuario(Usuario usuario);
    
    void validarEmail(String email);
    
    Optional<Usuario> obterPorId(Long id);
}
