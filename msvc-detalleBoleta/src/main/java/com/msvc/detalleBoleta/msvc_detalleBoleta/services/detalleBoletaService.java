package com.msvc.detalleBoleta.msvc_detalleBoleta.services;

import com.msvc.detalleBoleta.msvc_detalleBoleta.dtos.DetalleBoletaDTO;
import com.msvc.detalleBoleta.msvc_detalleBoleta.model.detalleBoletaModel;

import java.util.List;

public interface detalleBoletaService {

    List<DetalleBoletaDTO> findAll();
    detalleBoletaModel findById(Long idDetalleBoleta);
    detalleBoletaModel save(detalleBoletaModel detalleBoletaModel);
    List<detalleBoletaModel> findAllByProductoId(Long idProducto);

}
