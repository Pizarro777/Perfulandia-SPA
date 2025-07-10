package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.excepcions.ProveedorException;
import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService{


    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Proveedor findById(Long id) throws ProveedorException {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException("El proveedor con id " + id + " no se encuentra en la base de datos"));
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }


    @Override
    @Transactional
    public Proveedor save (Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    @Transactional
    public Proveedor actualizarProveedor(Long idProveedor, Proveedor proveedorActualizado) {

        Proveedor proveedorExistente = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new ProveedorException("Proveedor no encontrado con id " + idProveedor));


        if (proveedorActualizado.getNombreProveedor() == null || proveedorActualizado.getNombreProveedor().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio para la actualización.");
        }

        Optional<Proveedor> existingProveedorWithNewNameOpt = proveedorRepository.findByNombreProveedor(proveedorActualizado.getNombreProveedor());
        if (existingProveedorWithNewNameOpt.isPresent()) {
            Proveedor proveedorConMismoNombre = existingProveedorWithNewNameOpt.get();
            if (!proveedorConMismoNombre.getIdProveedor().equals(idProveedor)) {
                throw new IllegalArgumentException("Ya existe otro proveedor con el nombre: " + proveedorActualizado.getNombreProveedor());
            }
        }

        // Update fields
        proveedorExistente.setNombreProveedor(proveedorActualizado.getNombreProveedor());

        if (proveedorActualizado.getTelefono() != null) {
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
        }

        if (proveedorActualizado.getDireccion() != null && !proveedorActualizado.getDireccion().trim().isEmpty()) {
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
        }

        if (proveedorActualizado.getServicio() != null && !proveedorActualizado.getServicio().trim().isEmpty()) {
            proveedorExistente.setServicio(proveedorActualizado.getServicio());
        }

        System.out.println("DEBUG Service: Actualizando proveedor con ID: " + proveedorExistente.getIdProveedor() +
                " Nombre: " + proveedorExistente.getNombreProveedor() +
                " Teléfono: " + proveedorExistente.getTelefono() +
                " Dirección: " + proveedorExistente.getDireccion() +
                " Servicio: " + proveedorExistente.getServicio());

        return proveedorRepository.save(proveedorExistente);
    }

    @Override
    @Transactional
    public void eliminarProveedor(Long idProveedor) {
        if (!proveedorRepository.existsById(idProveedor)) {
            throw new ProveedorException("Proveedor no encontrado para eliminar con ID: " + idProveedor);
        }
        proveedorRepository.deleteById(idProveedor);
        System.out.println("DEBUG Service: Proveedor eliminado con ID: " + idProveedor);
    }
}
