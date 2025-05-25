package com.pizarro777.msvc.inventarios.repositories;

import com.pizarro777.msvc.inventarios.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByIdProductoAndIdSucursal(Long idProducto, Long idSucursal);
}
