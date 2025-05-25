package com.pizarro777.msvc.sucursal.repositories;

import com.pizarro777.msvc.sucursal.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
}
