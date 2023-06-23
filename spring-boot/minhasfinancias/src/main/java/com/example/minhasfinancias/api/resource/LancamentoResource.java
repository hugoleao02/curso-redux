package com.example.minhasfinancias.api.resource;

import com.example.minhasfinancias.api.dto.AtualizaStatusDTO;
import com.example.minhasfinancias.api.dto.LancamentoDTO;
import com.example.minhasfinancias.exception.RegraNegocioException;
import com.example.minhasfinancias.model.entity.Lancamento;
import com.example.minhasfinancias.model.entity.StatusLancamento;
import com.example.minhasfinancias.model.entity.TipoLancamento;
import com.example.minhasfinancias.model.entity.Usuario;
import com.example.minhasfinancias.services.LancamentoService;
import com.example.minhasfinancias.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
public class LancamentoResource {
    
    private  final LancamentoService service;
    private  final UsuarioService usuarioService;
    
    
    
   
    
    @GetMapping
    public  ResponseEntity<Object> buscar(
            @RequestParam(name = "descricao", required = false) String descricao,
            @RequestParam(name = "tipo ", required = false) String tipo,
            @RequestParam(name = "mes", required = false) Integer mes,
            @RequestParam(name = "ano", required = false) Integer ano,
            @RequestParam(name = "usuario") Long idUsuario){
    
        Lancamento lancamentoFiltro = new Lancamento();
        lancamentoFiltro.setDescricao(descricao);
        lancamentoFiltro.setMes(mes);
        lancamentoFiltro.setAno(ano);
        
        Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
        if (!usuario.isPresent()){
            return  ResponseEntity.badRequest().body("Não foi possivel realizar a consulta. Usuário não encontrado.");
        }else {
            lancamentoFiltro.setUsuario(usuario.get());
        }
        List<Lancamento> lancamentos = service.buscar(lancamentoFiltro);
        return  ResponseEntity.ok(lancamentos);
    }
    
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody LancamentoDTO dto){
        try {
            Lancamento entidade = converter(dto);
            entidade = service.salvar(entidade);
            return new ResponseEntity<>(entidade, HttpStatus.CREATED);
        } catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
    @PutMapping("{id}/atualiza-status")
    public ResponseEntity<?> atualizarStatus(@PathVariable("id")Long id, @RequestBody AtualizaStatusDTO dto){
        return service.obterPorId(id).map(entity ->{
            StatusLancamento statusSelecionado = StatusLancamento.valueOf(dto.getStatus());
            if (statusSelecionado == null){
                return ResponseEntity.badRequest().body("Não foi possivel atualizar o status do lançamento, envie um status válido");
            }
            try{
                entity.setStatus(statusSelecionado);
                service.atualizar(entity);
                return ResponseEntity.ok(entity);
            }catch (RegraNegocioException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            
        }).orElseGet(() ->
                new ResponseEntity<>("Lancamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
    }
    
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto){
       return service.obterPorId(id).map( entity ->{
           try {
               Lancamento lancamento = converter(dto);
               lancamento.setId(entity.getId());
               service.atualizar(lancamento);
               return ResponseEntity.ok(lancamento);
           }catch (RegraNegocioException e){
               return ResponseEntity.badRequest().body(e.getMessage());
           }
        }).orElseGet(() -> new ResponseEntity<>("Lançamento não encotrado na base de dados.", HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("{id}")
    public  ResponseEntity<Object> deletar(@PathVariable("id") Long id){
        return service.obterPorId(id).map(entidade ->{
            service.deletar(entidade);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>("Lançamento não encotrado na base de dados.", HttpStatus.BAD_REQUEST));
    }
    
    
    private Lancamento converter(LancamentoDTO dto){
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());
        lancamento.setValor(dto.getValor());
        
        Usuario usuario = usuarioService
                .obterPorId(dto.getUsuario())
                .orElseThrow(()
                -> new RegraNegocioException("Usuário não encotrado para id informado"));
        
        lancamento.setUsuario(usuario);
        if (dto.getTipo() != null) {
            lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
        }
        if (dto.getStatus() != null) {
            lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
        }
        return lancamento;
    }
}
