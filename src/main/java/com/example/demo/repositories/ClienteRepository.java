package com.example.demo.repositories;

import com.example.demo.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Cliente getByCodigo(long codigo);

}
