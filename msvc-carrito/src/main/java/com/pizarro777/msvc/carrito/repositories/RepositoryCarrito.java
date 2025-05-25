package com.pizarro777.msvc.carrito.repositories;


import com.pizarro777.msvc.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryCarrito  extends JpaRepository<Carrito, Long> {

    List<Carrito> findByIdProducto(Long idProducto);
}
