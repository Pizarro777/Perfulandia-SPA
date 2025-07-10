package com.pizarro777.msvc.sucursales.services;

import com.pizarro777.msvc.sucursales.models.Sucursal;

import java.util.List;

public interface SucursalService {

    List<Sucursal> findAll();

    Sucursal findById(Long id);

    Sucursal save(Sucursal sucursal);

    Sucursal actualizarSucursal(Long id, Sucursal sucursal);

    void eliminarSucursal(Long id);
}
