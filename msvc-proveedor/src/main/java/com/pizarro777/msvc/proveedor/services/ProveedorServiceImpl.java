package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<ProveedorModel> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public ProveedorModel findById(Long idProveedor) {
        return proveedorRepository.findById(idProveedor).orElse(null);
    }

    @Override
    public ProveedorModel save(ProveedorModel proveedor) {
        return proveedorRepository.save(proveedor);
    }
}
