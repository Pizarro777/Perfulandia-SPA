package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.entities.Proveedor;

import java.util.List;

public interface ProveedorService {
    List<Proveedor> findAll();
    Proveedor findById(Long idProveedor);
    Proveedor save(Proveedor proveedor);
    void eliminarProveedor(Long id);
}
