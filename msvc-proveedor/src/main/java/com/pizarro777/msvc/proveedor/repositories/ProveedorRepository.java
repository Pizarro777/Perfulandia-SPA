package com.pizarro777.msvc.proveedor.repositories;


import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorModel, Long> {

    List<ProveedorModel> findByIdProveedor(Long idDProveedor);

    List<ProveedorModel> findByIdProducto(Long idProducto);

    List<ProveedorModel> findAllByIdBoletas(Long idBoletas);

}
