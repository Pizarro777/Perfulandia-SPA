package com.pizarro777.msvc.proveedor.repositories;

import com.msvc.proveedor.msvc_proveedor.model.proveedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProveedorRepository extends JpaRepository<proveedorModel, Long> {

    List<proveedorModel> findByIpdroveedorDetalleBoleta(Long idProveedor);

    List<proveedorModel> findByIdproveedor(Long idProducto);

    List<proveedorModel> findAllByIdBoletas(Long idBoletas);
}
