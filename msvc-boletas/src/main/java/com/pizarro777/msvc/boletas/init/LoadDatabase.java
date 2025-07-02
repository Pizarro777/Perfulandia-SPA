package com.pizarro777.msvc.boletas.init;

import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import net.datafaker.Faker;
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
                Boletas boleta = new Boletas();

                String nombreBoleta = faker.commerce().productName();

                double precioBoleta = faker.number().numberBetween(1000, 10000);

                boleta.setNombreBoletas(nombreBoleta);
                boleta.setPrecioBoletas(precioBoleta);

                boleta = boletasRepository.save(boleta);
                log.info("Boleta creada: {}", boleta);
            }
        } else {
            log.info("Boletas ya existentes en base, no se insertan datos.");
        }
    }

}
