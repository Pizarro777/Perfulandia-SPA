package com.pizarro777.msvc.sucursales.services;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal actualizarSucursal(Long id, Sucursal sucursal) {
        return sucursalRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(sucursal.getNombre());
                    existing.setDireccion(sucursal.getDireccion());
                    existing.setCiudad(sucursal.getCiudad());
                    return sucursalRepository.save(existing);
                }).orElse(null);
    }

    @Override
    public boolean eliminarSucursal(Long id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
