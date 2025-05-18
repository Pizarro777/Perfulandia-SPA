package com.pizarro777.msvc.inventarios.repositories;

import com.pizarro777.msvc.inventarios.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
