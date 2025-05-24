package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorModel> findAll() {
        return proveedorRepository.findAll();
    }

    public ProveedorModel findById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public ProveedorModel save(ProveedorModel proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

}


