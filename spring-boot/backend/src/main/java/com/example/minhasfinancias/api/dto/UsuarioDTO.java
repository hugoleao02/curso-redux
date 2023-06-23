package com.example.minhasfinancias.api.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    private String nome;
    
    private String email;
    private String senha;
}
