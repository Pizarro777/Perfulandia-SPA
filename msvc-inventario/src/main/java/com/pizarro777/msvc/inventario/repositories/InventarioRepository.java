package com.pizarro777.msvc.inventario.repositories;

import com.pizarro777.msvc.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByIdProductoAndIdSucursal(Long idProducto, Long idSucursal);
}
