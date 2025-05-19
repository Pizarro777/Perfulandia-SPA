package com.pizarro777.msvc.proveedor.repositories;


import com.pizarro777.msvc.inventarios.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    Optional<Proveedor> findByIdProducto(Long idProducto);
}
