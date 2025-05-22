package com.pizarro777.msvc.boletas.repositories;

import com.pizarro777.msvc.boletas.models.Boletas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletasRepository extends JpaRepository<Boletas, Long> {

    @Query("SELECT b FROM Boletas b WHERE b.nombre = :nombre")
    List<Boletas> buscarPorNombre(@Param("nombres") String nombre);

}
