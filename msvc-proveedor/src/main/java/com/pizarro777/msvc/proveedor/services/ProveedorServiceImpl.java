package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ProveedorServiceImpl implements ProveedorService{


    private final ProveedorRepository proveedorRepository;


    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }


    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findById(Long idProveedor) {
        return proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id " + idProveedor));
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}
