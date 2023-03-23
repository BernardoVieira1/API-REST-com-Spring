package com.algaworks.algalog.algalogapi.Controllers;

import javax.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.algalogapi.assembler.RegistroOcorrenciaAssembler;
import com.algaworks.algalog.algalogapi.model.OcorrenciaMode;
import com.algaworks.algalog.algalogapi.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.Models.Entrega;
import com.algaworks.algalog.domain.Models.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private  BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private RegistroOcorrenciaAssembler registroOcorrenciaAssembler;
    


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaMode registrar(@PathVariable Long entregaId,
        @Valid @RequestBody OcorrenciaInput ocorrenciaInput){

        Ocorrencia ocorrenciaRegistrda = registroOcorrenciaService
            .registrar(entregaId, ocorrenciaInput.getDescricao());

        return registroOcorrenciaAssembler.toModel(ocorrenciaRegistrda);
    }

    @GetMapping
    public List<OcorrenciaMode> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return registroOcorrenciaAssembler.toCollectionModel(entrega.getOcorrencias()); 
    }

} 
