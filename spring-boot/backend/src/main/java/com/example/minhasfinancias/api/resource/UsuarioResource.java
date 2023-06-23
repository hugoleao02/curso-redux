package com.example.minhasfinancias.api.resource;

import com.example.minhasfinancias.api.dto.UsuarioDTO;
import com.example.minhasfinancias.exception.ErroAutenticacao;
import com.example.minhasfinancias.exception.RegraNegocioException;
import com.example.minhasfinancias.model.entity.Usuario;
import com.example.minhasfinancias.services.LancamentoService;
import com.example.minhasfinancias.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {
    
    private  final UsuarioService service;
    private  final LancamentoService lancamentoService;
    
  
    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){
        
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        }catch (ErroAutenticacao e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody UsuarioDTO dto) {
        
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        
        try {
            Usuario usuarioSalvo = service.salvaUsuario(usuario);
            return new ResponseEntity<Object>(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
    @GetMapping("{id}/saldo")
    public ResponseEntity<Object> obterSaldo(@PathVariable("id") Long id){
        Optional<Usuario> usuario = service.obterPorId(id);
        
        if (usuario.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }
    
    
}
