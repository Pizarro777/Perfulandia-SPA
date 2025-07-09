package com.pizarro777.msvc.productos.init;

import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    private final Faker faker = new Faker();


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setMarca(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(Double.parseDouble(faker.commerce().price(10000.0, 100000.0)));
            producto.setStock(faker.number().numberBetween(1,10000));

            productoRepository.save(producto);
        }
        System.out.println("Se ha generado productos de prueba.");
        productoRepository.findAll().forEach(System.out::println);
    }
}
