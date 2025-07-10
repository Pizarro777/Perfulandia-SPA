package com.pizarro777.msvc.proveedor.repositories;


import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
