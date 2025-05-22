package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BoletasServicelmpl implements BoletasService{

    @Autowired
    private BoletasRepository boletasRepository;
}
