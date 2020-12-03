package com.example.demo.DTOs;

import java.time.LocalDate;

import com.example.demo.models.Cliente;
import com.example.demo.models.Reserva;
import com.example.demo.models.Veiculo;

public class ReservaDTO {
    
    private Cliente cliente;

    private Veiculo veiculo;

    private LocalDate dataDeInicio;

    private LocalDate dataDeFim;

    public ReservaDTO(Cliente cliente, Veiculo veiculo, LocalDate dataDeInicio, LocalDate dataDeFim) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataDeInicio = dataDeInicio;
        this.dataDeFim = dataDeFim;
    }

    public Reserva transformaParaObjeto() {
        return new Reserva(this.cliente, this.veiculo, this.dataDeInicio, this.dataDeFim);
    }

}
