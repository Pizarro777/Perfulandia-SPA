package com.pizarro777.msvc.boletas.repositories;

import com.pizarro777.msvc.boletas.models.entities.Boletas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoletasRepository extends JpaRepository<Boletas, Long> {
    Optional<Boletas> findByNombreBoletas(String nombreBoletas);



}
