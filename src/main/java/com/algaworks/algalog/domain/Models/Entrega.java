package com.algaworks.algalog.domain.Models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
    
    //@Valid
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Valid
    // @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    // @NotNull
    @ManyToOne
    private Cliente cliente;

    // @Valid
    // @NotNull
    @Embedded
    private Destinatario destinatario;

    //@NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();
    
    //@JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;
    
   // @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataPedido;

   // @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;

    }


    public void finalizar(){
        if(naoPodeSerFinalizado()){
            throw new NegocioException("Entrega não pode ser finalizada");
            
        }
        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }


    public boolean naoPodeSerFinalizado() {
        return StatusEntrega.PENDENTE.equals(getStatus());
    }

    public boolean podeSerFinalizado() {
        return !podeSerFinalizado();
    }

}
