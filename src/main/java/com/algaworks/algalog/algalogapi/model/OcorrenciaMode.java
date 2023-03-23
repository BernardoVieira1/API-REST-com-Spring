package com.algaworks.algalog.algalogapi.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaMode {
    
    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
