package com.pizarro777.msvc.proveedor.init;

import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
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
    ProveedorRepository proveedorRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));

        if(proveedorRepository.count() == 0){
            for(int i=0;i<100;i++){
                Proveedor proveedor = new Proveedor();

                String numeroStr = faker.idNumber().valid().replaceAll("-","");
                proveedor = proveedorRepository.save(proveedor);
                log.info("El medico creado es {}", proveedor);
            }
        }


    }
}
