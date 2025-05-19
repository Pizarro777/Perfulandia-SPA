package com.msvc.detalleBoleta.msvc_detalleBoleta.repositories;

import com.msvc.detalleBoleta.msvc_detalleBoleta.model.detalleBoletaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface detalleBoletaRepository  extends JpaRepository<detalleBoletaModel, Long> {
    List<detalleBoletaModel> findByIdDetalleBoleta(Long idDetalleBoleta);

    List<detalleBoletaModel> findByIdProducto(Long idProducto);

    List<detalleBoletaModel> findAllByIdBoletas(Long idBoletas);
}
