package com.algaworks.algalog.algalogapi.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algalog.algalogapi.model.OcorrenciaMode;
import com.algaworks.algalog.domain.Models.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RegistroOcorrenciaAssembler {
    
    private ModelMapper modelMapper;

    public OcorrenciaMode toModel(Ocorrencia ocorrencia){
        return modelMapper.map(ocorrencia,OcorrenciaMode.class);
    }

    public List<OcorrenciaMode>  toCollectionModel(List<Ocorrencia> ocorrencia){
        return ocorrencia.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

}
