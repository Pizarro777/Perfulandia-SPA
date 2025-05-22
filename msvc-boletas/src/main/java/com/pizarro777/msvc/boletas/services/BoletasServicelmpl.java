package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.models.Boletas;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletasServicelmpl implements BoletasService{

    @Autowired
    private BoletasRepository boletasRepository;

    @Override
    public List<Boletas> findAll() { return this.boletasRepository.findAll()}

    @Override
    public Boletas findById(Long id) {
        return this.boletasRepository.findById(id).orElseThrow(
                () -> new BoletasException("La boleta con el id: " +id+ "no se encuentra en la bases de datos")
        );
    }

    @Override
    public Boletas save(Boletas boletas) {
        if(this.boletasRepository.findByNombre(boletas.getNombre()).isPresent()){
            throw new BoletasException("La boleta con el nombre: "+ boletas.getNombreBoletas()
                    + "ya existe en la bases de datos");
        }
        return this.boletasRepository.save(boletas);
    }
}
