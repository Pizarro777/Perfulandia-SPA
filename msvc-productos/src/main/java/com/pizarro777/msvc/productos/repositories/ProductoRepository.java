package com.pizarro777.msvc.productos.repositories;

import com.pizarro777.msvc.productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
