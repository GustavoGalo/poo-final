package com.example.demo.DTOs;

import com.example.demo.models.Cliente;

public class ClienteDTO {

    private String nome;

    private String endereco;

    private String cpf;

    public ClienteDTO(String nome, String endereco, String cpf) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Cliente tranformaParaObjeto() {
        return new Cliente(nome, endereco, cpf);
    }

}
