package com.example.demo.controllers;

import java.util.List;

import javax.validation.ConstraintDefinitionException;
import javax.validation.Valid;

import com.example.demo.DTOs.ClienteDTO;
import com.example.demo.models.Cliente;
import com.example.demo.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(value = "/clientes")
    public ResponseEntity<List<Cliente>> list() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping(value = "/cliente/{codigo}")
    public ResponseEntity<Cliente> get(@PathVariable(value = "codigo") long codigo) {
        Cliente cliente = clienteRepository.getByCodigo(codigo);
        
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cliente);
        }

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            throw new ConstraintDefinitionException((bindResult.getAllErrors().get(0).getDefaultMessage()));
        }
        Cliente clienteInserido = clienteRepository.save(clienteDTO.tranformaParaObjeto());
        return ResponseEntity.ok().body(clienteInserido);
    }

    @DeleteMapping(value = "/cliente/{codigo}")
    public ResponseEntity<Cliente> delete(@PathVariable(value = "codigo") long codigo) {
        Cliente cliente = clienteRepository.getByCodigo(codigo);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cliente);
        }

        clienteRepository.delete(cliente);

        return ResponseEntity.ok().body(cliente);
    }
    
    @PutMapping(value = "/cliente/{codigo}")
    public ResponseEntity<Cliente> update(@PathVariable(value = "codigo") long codigo, @RequestBody Cliente body) {
        Cliente cliente = clienteRepository.getByCodigo(codigo);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cliente);
        }

        cliente.setCpf(body.getCpf());
        cliente.setNome(body.getNome());
        cliente.setEndereco(body.getEndereco());

        clienteRepository.save(cliente);

        return ResponseEntity.ok().body(cliente);
    }

}
