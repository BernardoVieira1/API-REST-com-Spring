package com.algaworks.algalog.algalogapi.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.Models.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
  

    @Autowired
    private ClienteRepository clienteRepository;

    private CatalogoClienteService catalogoClienteService;


    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());


        // Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        // return cliente.orElse(null);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        //return clienteRepository.save(cliente);
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,@RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        //cliente = clienteRepository.save(cliente);
        cliente = catalogoClienteService.salvar(cliente);


        return ResponseEntity.ok(cliente);
        // Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        // return cliente.orElse(null);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        //clienteRepository.deleteById(clienteId);
        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();

    }


}
