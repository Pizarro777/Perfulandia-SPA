package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.excepcions.ProveedorException;
import com.pizarro777.msvc.proveedor.models.entities.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    List<Proveedor> findAll();

    Proveedor save(Proveedor proveedor);
    Proveedor actualizarProveedor(Long id, Proveedor proveedor);
    void eliminarProveedor(Long id) throws ProveedorException;

    Proveedor findById(Long id);


}
