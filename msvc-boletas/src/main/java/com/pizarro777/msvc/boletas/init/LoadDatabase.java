package com.pizarro777.msvc.boletas.init;

import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    BoletasRepository boletasRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));

        if(boletasRepository.count() == 0){
            for(int i=0;i<100;i++){
                Boletas boletas = new Boletas();

                String numeroStr = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroStr.substring(numeroStr.length()-1);
                String restante = numeroStr.substring(0, numeroStr.length()-1);

                boletas = boletasRepository.save(boletas);
                log.info("El medico creado es {}", boletas);
            }
        }


    }





}
