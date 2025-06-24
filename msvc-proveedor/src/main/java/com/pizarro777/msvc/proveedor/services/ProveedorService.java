package com.pizarro777.msvc.proveedor.services;

import java.util.List;

public interface ProveedorService {
    List<Proveedor> findAll();
    Proveedor findById(Long id);
    Proveedor save(Proveedor proveedor);
    void delete(Long id);
}
