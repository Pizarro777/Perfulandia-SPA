package com.pizarro777.msvc.boletas.repositories;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoletasRepository extends JpaRepository<BoletasModel, Long> {

}
