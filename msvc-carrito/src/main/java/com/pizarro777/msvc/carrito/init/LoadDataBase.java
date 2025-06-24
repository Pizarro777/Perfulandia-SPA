package com.pizarro777.msvc.carrito.init;

import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.model.ItemCarrito;
import com.pizarro777.msvc.carrito.repositories.RepositoryCarrito;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Profile("dev") // Solo se ejecuta en el perfil "dev"
@Component
public class LoadDataBase implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
    private final RepositoryCarrito repositoryCarrito;

    public LoadDataBase(RepositoryCarrito repositoryCarrito) {
        this.repositoryCarrito = repositoryCarrito;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));

        if (repositoryCarrito.count() == 0) {
            for (int i = 0; i < 100; i++) {
                Carrito carrito = new Carrito();
                List<ItemCarrito> items = new ArrayList<>();

                int cantidadItems = faker.number().numberBetween(1, 5); // 1 a 5 items

                for (int j = 0; j < cantidadItems; j++) {
                    ItemCarrito item = new ItemCarrito();
                    item.setIdProducto(faker.number().numberBetween(1L, 100L));
                    item.setCantidad(faker.number().numberBetween(1, 10));
                    item.setPrecio(faker.number().randomDouble(2, 1000, 10000));
                    item.setCarrito(carrito);
                    items.add(item);
                }

                carrito.setItems(items);

                carrito = repositoryCarrito.save(carrito);
                log.info("Carrito creado con items: {}", carrito);
            }
        }
    }
}
