package com.example.minhasfinancias.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lancamento")
public class Lancamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    private String descricao;
    
    @Column
    private Integer mes;
    @Column
    private Integer ano;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column
    private BigDecimal valor;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    @Column
    @Enumerated(value = EnumType.STRING)
    private TipoLancamento tipo;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusLancamento status;
    
}
