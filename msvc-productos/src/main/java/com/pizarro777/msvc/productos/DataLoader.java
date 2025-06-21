package com.pizarro777.msvc.productos;

import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Random;


@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    private final Faker faker = new Faker(new Locale("es", "CL"));
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Producto producto = generarProducto();
            productoRepository.save(producto);
        }
        System.out.println("Se ha generado productos de prueba.");
    }

    private Producto generarProducto() {
        Producto producto = new Producto();
        producto.setNombre(faker.commerce().productName());
        producto.setMarca(faker.commerce().productName());
        producto.setDescripcion(faker.lorem().sentence());
        producto.setPrecio(Double.parseDouble(faker.commerce().price(10000, 100000)));
        producto.setStock(random.nextInt(101));
        return producto;
    }
    List<Producto> producto = productoRepository.findAll();


}
