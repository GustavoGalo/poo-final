package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @NotBlank(message = "Modelo do carro deve ser fornecido.")
    private String modelo;

    @NotBlank(message = "O valor cobrado por dia deve ser fornecido.")
    private double valorPorDia;

    public Veiculo() {

    }

    public Veiculo(String modelo, double valorPorDia) {
        this.modelo = modelo;
        this.valorPorDia = valorPorDia;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorPorDia() {
        return valorPorDia;
    }

    public void setValorPorDia(double valorPorDia) {
        this.valorPorDia = valorPorDia;
    }

}
