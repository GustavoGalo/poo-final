package com.example.demo.repositories;

import com.example.demo.models.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    public Reserva getByCodigo(long codigo);

}
