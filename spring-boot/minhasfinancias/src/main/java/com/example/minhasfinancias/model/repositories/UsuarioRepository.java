package com.example.minhasfinancias.model.repositories;

import com.example.minhasfinancias.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    
    
    boolean existsByEmail(String email);
    
    Optional<Usuario> findByEmail(String email);
    
}