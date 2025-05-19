package com.pizarro777.msvc.proveedor.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProveedorService {

    @Autowired
    private proveedorRepositry proveedorRepository;

    public List<proveedorModel> findAll() {
        return proveedorRepository.findAll();
    }

    public proveedorModel findById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public proveedorModel save(proveedorModel proveedorModel) {
        return proveedorRepository.save(proveedorModel);
    }

    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }
}
