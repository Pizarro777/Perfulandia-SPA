package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository repository, ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProveedorModel> findAll() {return proveedorRepository.findAll();}

    public ProveedorModel findById(Long idProveedor) {
        return proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id "+ idProveedor));
    }

    public static ProveedorModel save(ProveedorModel proveedorModel) {
        return proveedorRepository.save(proveedorModel);
    }

    public void delete(Long idProveedor) {
        proveedorRepository.deleteById(idProveedor); }


}


