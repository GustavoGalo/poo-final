package com.example.demo.controllers;

import java.util.List;

import javax.validation.ConstraintDefinitionException;
import javax.validation.Valid;

import com.example.demo.DTOs.VeiculoDTO;
import com.example.demo.models.Veiculo;
import com.example.demo.repositories.VeiculoRepository;

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
public class VeiculoController {
    
    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping(value = "/veiculos")
    public ResponseEntity<List<Veiculo>> list() {
        List<Veiculo> veiculos = veiculoRepository.findAll();

        if (veiculos == null || veiculos.size() == 0) {
            return new ResponseEntity<List<Veiculo>>(veiculos, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Veiculo>>(veiculos, HttpStatus.OK);
    }

    @GetMapping(value = "/veiculo/{codigo}")
    public ResponseEntity<Veiculo> get(@PathVariable(value = "codigo") long codigo) {
        Veiculo veiculo = veiculoRepository.getByCodigo(codigo);
        if (veiculo == null) {
            return new ResponseEntity<Veiculo>(veiculo, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }
    
    @PostMapping(value = "/veiculo")
    public ResponseEntity<Veiculo> create(@Valid @RequestBody VeiculoDTO veiculoDTO, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            throw new ConstraintDefinitionException((bindResult.getAllErrors().get(0).getDefaultMessage()));
        }

        Veiculo veiculoInserido = veiculoRepository.save(veiculoDTO.transformaParaObjeto());
        return new ResponseEntity<Veiculo>(veiculoInserido, HttpStatus.OK);
    }

    @DeleteMapping(value = "/veiculo/{codigo}")
    public ResponseEntity<Veiculo> delete(@PathVariable(value = "codigo") long codigo) {
        Veiculo veiculo = veiculoRepository.getByCodigo(codigo);

        if (veiculo == null) {
            return new ResponseEntity<Veiculo>(veiculo, HttpStatus.NOT_FOUND);
        }

        veiculoRepository.delete(veiculo);

        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }

    @PutMapping(value = "/veiculo/{codigo}")
    public ResponseEntity<Veiculo> update(@PathVariable(value = "codigo") long codigo, @RequestBody Veiculo body) {
        Veiculo veiculo = veiculoRepository.getByCodigo(codigo);

        if (veiculo == null) {
            return new ResponseEntity<Veiculo>(veiculo, HttpStatus.NOT_FOUND);
        }

        veiculo.setCodigo(body.getCodigo());
        veiculo.setModelo(body.getModelo());
        veiculo.setValorPorDia(body.getValorPorDia());

        veiculoRepository.save(veiculo);

        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }
}
