package com.pizarro777.msvc.clientes.init;

import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository repo;

    private final Faker faker = new Faker();

    private String generarRut() {
        int num = faker.number().numberBetween(1000000, 99999999);
        char dv = "0123456789K".charAt(faker.number().numberBetween(0, 11));
        return num + "-" + dv;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Cliente cli = new Cliente();
            cli.setNombre(faker.name().firstName());
            cli.setApellido(faker.name().lastName());
            cli.setRut(generarRut());
            cli.setDireccion(faker.address().fullAddress());
            cli.setCorreo(faker.internet().emailAddress());

            repo.save(cli);
        }
        System.out.println("Se ha generado clientes de prueba.");
        repo.findAll().forEach(System.out::println);
    }
}
