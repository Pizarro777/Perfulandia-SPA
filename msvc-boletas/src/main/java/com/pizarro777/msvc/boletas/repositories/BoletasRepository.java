package com.pizarro777.msvc.boletas.repositories;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletasRepository extends JpaRepository<BoletasModel, Long> {

}
