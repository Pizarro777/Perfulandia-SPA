package com.pizarro777.msvc.sucursal.services;

import com.pizarro777.msvc.sucursal.models.Sucursal;
import com.pizarro777.msvc.sucursal.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository repository;

    public Sucursal crearSucursal(Sucursal sucursal) {
        return repository.save(sucursal);
    }

    public Optional<Sucursal> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public List<Sucursal> listarTodas() {
        return repository.findAll();
    }

    public void eliminarSucursal(Long id) {
        repository.deleteById(id);
    }
}
