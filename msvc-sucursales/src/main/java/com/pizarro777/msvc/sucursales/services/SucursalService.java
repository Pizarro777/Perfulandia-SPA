package com.pizarro777.msvc.sucursales.services;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    private final SucursalRepository repository;

    public SucursalService(SucursalRepository repository) {
        this.repository = repository;
    }

    /* Crear Sucursal */
    public Sucursal crearSucursal(Sucursal sucursal) {
        return repository.save(sucursal);
    }

    /* Obtener por ID */
    public Sucursal obtenerPorId(Long id) {
        Optional<Sucursal> sucursal = repository.findById(id);
        return sucursal.orElseThrow(() ->
                new RuntimeException("No se encontró el id en la base de datos: " + id));
    }

    /* Listar todas */
    public List<Sucursal> listarTodas() {
        return repository.findAll();
    }

    /* Eliminar Sucursal */
    public void eliminarSucursal(Long id) {
        repository.deleteById(id);
    }
}

