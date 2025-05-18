package com.msvc.detalleBoleta.msvc_detalleBoleta.services;


import com.msvc.detalleBoleta.msvc_detalleBoleta.clients.ProductoClientRest;
import com.msvc.detalleBoleta.msvc_detalleBoleta.dtos.DetalleBoletaDTO;
import com.msvc.detalleBoleta.msvc_detalleBoleta.model.detalleBoletaModel;
import com.msvc.detalleBoleta.msvc_detalleBoleta.repositories.detalleBoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class detalleBoletaServiceImpl {

    @Autowired
    private detalleBoletaRepository detalleBoletaRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public List<DetalleBoletaDTO> findAll() {
        return detalleBoletaRepository.findAll().stream()
                .map(detalleBoletaModel -> new DetalleBoletaDTO(
                        detalleBoletaModel.getIdDetalleBoleta(),
                        detalleBoletaModel
                ))
    }



}
