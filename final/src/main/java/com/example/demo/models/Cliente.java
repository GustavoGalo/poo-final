package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;

    @NotBlank(message = "Campo endereço é obrigatório.")
    private String endereco;

    @Column(length = 14)
    @NotBlank(message = "Campo CPF é obrigatório.")
    @Size(min = 14, max = 14, message = "O CPF deve conter 14 caracteres no formato xxx.xxx.xxx-xx")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "O CPF deve ter formato xxx.xxx.xxx-xx")
    private String cpf;

    private boolean test = false;

    public Cliente() {

    }

    public Cliente(String nome, String endereco, String cpf) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
    
}
