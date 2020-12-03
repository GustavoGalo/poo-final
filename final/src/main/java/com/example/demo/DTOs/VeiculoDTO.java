package com.example.demo.DTOs;

import com.example.demo.models.Veiculo;

public class VeiculoDTO {

    private String modelo;

    private double valorPorDia;
    
    public VeiculoDTO(String modelo, double valorPorDia) {
        this.modelo = modelo;
        this.valorPorDia = valorPorDia;
    }

    public Veiculo transformaParaObjeto() {
        return new Veiculo(modelo, valorPorDia);
    }
}
