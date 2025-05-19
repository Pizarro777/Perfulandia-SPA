package com.msvc.detalleBoleta.msvc_detalleBoleta.services;


import com.msvc.detalleBoleta.msvc_detalleBoleta.model.detalleBoletaModel;
import com.msvc.detalleBoleta.msvc_detalleBoleta.repositories.detalleBoletaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class detalleBoletaService {

    @Autowired
    private detalleBoletaRepository detalleBoletaRepository;

    public List<detalleBoletaModel> findAll() {
        return detalleBoletaRepository.findAll();
    }

    public detalleBoletaModel findById(Long id) {
        return detalleBoletaRepository.findById(id).orElse(null);
    }

    public detalleBoletaModel save(detalleBoletaModel detalleBoletaModel) {
        return detalleBoletaRepository.save(detalleBoletaModel);
    }

    public void deleteById(Long id) {
        detalleBoletaRepository.deleteById(id);
    }

}
