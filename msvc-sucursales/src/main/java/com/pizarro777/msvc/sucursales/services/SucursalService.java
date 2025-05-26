package com.pizarro777.msvc.sucursales.services;

import com.pizarro777.msvc.sucursales.dtos.SucursalDTO;
import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public Sucursal crearSucursalDesdeDTO(SucursalDTO sucursalDTO) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setCiudad(sucursalDTO.getCiudad());
        return sucursalRepository.save(sucursal);
    }

    public SucursalDTO convertirASucursalDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setCiudad(sucursal.getCiudad());

        return dto;
    }

    public Optional<Sucursal> obtenerPorId(Long id) {
        return sucursalRepository.findById(id);
    }

    public List<Sucursal> listarTodas() {
        return sucursalRepository.findAll();
    }

    public void eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }
}
