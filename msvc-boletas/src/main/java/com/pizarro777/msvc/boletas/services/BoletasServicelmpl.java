package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoletasServicelmpl implements BoletasService{

    @Autowired
    private BoletasRepository boletasRepository;

    @Override
    public list<Boletas> findAll() { return this.boletasRepository}

}
