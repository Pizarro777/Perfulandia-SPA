package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class BoletasService {

    @Autowired
    private BoletasRepository boletasRepository;

    public List<BoletasModel> findAll() {
        return boletasRepository.findAll();
    }

    public BoletasModel findById(Long id) {
        return boletasRepository.findById(id).orElse(null);
    }

    public BoletasModel save(BoletasModel detalleBoletaModel) {
        return boletasRepository.save(detalleBoletaModel);
    }

    public void deleteById(Long id) {
        boletasRepository.deleteById(id);
    }
}
