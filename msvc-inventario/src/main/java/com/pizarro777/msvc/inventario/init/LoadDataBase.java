package com.pizarro777.msvc.inventario.init;

import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    private final InventarioRepository inventarioRepository;

    public LoadDataBase(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("es", "CL"));

        if (inventarioRepository.count() == 0) {
            for (int i = 0; i < 50; i++) {
                Inventario inventario = new Inventario();
                inventario.setIdProducto(faker.number().numberBetween(1L, 50L));
                inventario.setIdSucursal(faker.number().numberBetween(1L, 10L));
                inventario.setCantidad(faker.number().numberBetween(1, 100));

                inventarioRepository.save(inventario);
                log.info("Inventario cargado: {}", inventario);
            }
        }
    }
}