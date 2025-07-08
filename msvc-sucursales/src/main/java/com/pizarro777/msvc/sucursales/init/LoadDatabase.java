package com.pizarro777.msvc.sucursales.init;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev") // Solo se ejecuta si está activo el perfil "dev"
@Component
public class LoadDatabase implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final SucursalRepository sucursalRepository;

    public LoadDatabase(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("es", "CL"));

        if (sucursalRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Sucursal sucursal = new Sucursal();
                sucursal.setNombre("Sucursal " + (i + 1));
                sucursal.setCiudad(faker.address().cityName());
                sucursal.setDireccion(faker.address().streetAddress());

                sucursalRepository.save(sucursal);
                log.info("Sucursal cargada: {}", sucursal);
            }
        }
    }
}