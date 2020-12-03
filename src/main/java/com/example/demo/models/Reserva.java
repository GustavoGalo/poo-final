package com.example.demo.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable=false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo", nullable=false)
    private Veiculo veiculo;

    @NotBlank(message = "A data de início deve ser informada.")
    @Pattern(regexp = "\\d{4}\\-\\d{2}\\-\\d{2}", message = "A data deve ser informada no formtado DD/MM/YYYY")
    private LocalDate dataDeInicio;

    @NotBlank(message = "A data de início deve ser informada.")
    @Pattern(regexp = "\\d{4}\\-\\d{2}\\-\\d{2}", message = "A data deve ser informada no formtado DD/MM/YYYY")
    private LocalDate dataDeFim;

    public Reserva() {

    }

    public Reserva(Cliente cliente, Veiculo veiculo, LocalDate dataDeInicio, LocalDate dataDeFim) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataDeInicio = dataDeInicio;
        this.dataDeFim = dataDeFim;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public LocalDate getDataDeFim() {
        return dataDeFim;
    }

    public void setDataDeFim(LocalDate dataDeFim) {
        this.dataDeFim = dataDeFim;
    }
   
}
