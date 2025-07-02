package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;

import java.util.List;

public interface ProveedorService {
    List<ProveedorModel> findAll();
    ProveedorModel findById(Long idProveedor);
    ProveedorModel save(ProveedorModel proveedor);
}
