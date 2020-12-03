package com.example.demo.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintDefinitionException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.demo.DTOs.ReservaDTO;
import com.example.demo.models.Reserva;
import com.example.demo.repositories.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservaController {

    @Autowired
    ReservaRepository reservaRepository;
    
    @GetMapping(value = "/reservas")
    public ResponseEntity<List<Reserva>> list() {
        List<Reserva> reservas = reservaRepository.findAll();

        if (reservas == null || reservas.size() == 0) {
            return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
    }
    
    @GetMapping(value = "/reservas/{codigo}")
    public ResponseEntity<Reserva> get(@PathParam(value = "codigo") long codigo) {
        Reserva reserva = reservaRepository.getByCodigo(codigo);

        if (reserva == null) {
            return new ResponseEntity<Reserva>(reserva, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
    }

    @GetMapping(value = "/reservas/porcliente/{codigoCliente}")
    public ResponseEntity<List<Reserva>> listPorCliente(@PathParam(value = "codigoCliente") long codigoCliente) {
        List<Reserva> reservas = reservaRepository.findAll(), reservasEncontradas = new ArrayList<Reserva>();

        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getCodigo() == codigoCliente) {
                reservasEncontradas.add(reserva);
            }
        }

        if (reservasEncontradas == null || reservasEncontradas.size() == 0) {
            return new ResponseEntity<List<Reserva>>(reservasEncontradas, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Reserva>>(reservasEncontradas, HttpStatus.OK);
    }

    @GetMapping(value = "/reservas/porveiculo/{codigoVeiculo}")
    public ResponseEntity<List<Reserva>> listPorVeiculo(@PathParam(value = "codigoVeiculo") long codigoVeiculo) {
        List<Reserva> reservas = reservaRepository.findAll(), reservasEncontradas = new ArrayList<Reserva>();

        for (Reserva reserva : reservas) {
            if (reserva.getVeiculo().getCodigo() == codigoVeiculo) {
                reservasEncontradas.add(reserva);
            }
        }

        if (reservasEncontradas == null || reservasEncontradas.size() == 0) {
            return new ResponseEntity<List<Reserva>>(reservasEncontradas, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Reserva>>(reservasEncontradas, HttpStatus.OK);
    }

    @PostMapping(value = "/reserva")
    public ResponseEntity<Reserva> create(@Valid @RequestBody ReservaDTO reservaDTO, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            throw new ConstraintDefinitionException((bindResult.getAllErrors().get(0).getDefaultMessage()));
        }

        boolean error = false;

        LocalDate dataDeInicio = reservaDTO.transformaParaObjeto().getDataDeInicio();
        LocalDate dataDeFim = reservaDTO.transformaParaObjeto().getDataDeFim();

        if (dataDeInicio.compareTo(dataDeFim) < 0) {
            // datas invalidas
            error = true;
        }
        else if (dataDeInicio.getDayOfWeek() == DayOfWeek.SUNDAY) {
            // data de início não pode cair em domingo
            error = true;
        }
        else if (dataDeFim.getDayOfWeek() == DayOfWeek.SUNDAY) {
            // data de fim não pode terminar em domingo
            error = true;
        }

        if (error) return new ResponseEntity<Reserva>(new Reserva(), HttpStatus.BAD_REQUEST);

        Reserva reserva = reservaRepository.save(reservaDTO.transformaParaObjeto());

        return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/reserva/{codigo}")
    public ResponseEntity<Reserva> delete(@PathParam(value = "codigo") long codigo) {
        Reserva reserva = reservaRepository.getByCodigo(codigo);

        if (reserva == null) {
            return new ResponseEntity<Reserva>(reserva, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
    }

    @PutMapping(value = "/reserva/{codigo}")
    public ResponseEntity<Reserva> update(@PathParam(value = "codigo") long codigo, @RequestBody Reserva body) {
        Reserva reserva = reservaRepository.getByCodigo(codigo);

        if (reserva == null) {
            return new ResponseEntity<Reserva>(reserva, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
    }
}
