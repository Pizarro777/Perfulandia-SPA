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

    public BoletasService(BoletasRepository repository, BoletasRepository proveedorRepository) {
        this.boletasRepository = repository;
    }

    public List<BoletasModel> findAll() {return boletasRepository.findAll();}

    public BoletasModel findById(Long idBoletas) {
        return boletasRepository.findById(idBoletas)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id "+ idBoletas));
    }

    public BoletasModel save(BoletasModel proveedorModel) {
        return boletasRepository.save(proveedorModel);
    }

    public void delete(Long idProveedor) {
        boletasRepository.deleteById(idProveedor); }



}
