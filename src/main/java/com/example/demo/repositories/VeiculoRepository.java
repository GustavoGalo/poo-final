package com.example.demo.repositories;

import com.example.demo.models.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    Veiculo getByCodigo(long codigo);

}
