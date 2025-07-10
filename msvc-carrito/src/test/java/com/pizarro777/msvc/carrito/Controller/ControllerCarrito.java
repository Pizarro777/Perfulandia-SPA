package com.pizarro777.msvc.carrito.Controller;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.model.ItemCarrito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerCarrito {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    public void testGetCarritoList() {
        // Crear y guardar un carrito
        Carrito nuevo = new Carrito();
        ItemCarrito item1 = new ItemCarrito();
        item1.setIdProducto(1L);
        item1.setNombre("Producto 1");
        item1.setMarca("Marca A");
        item1.setCantidad(1);
        item1.setPrecio(50.0);
        item1.setCarrito(nuevo);
        nuevo.getItems().add(item1);

        ItemCarrito item2 = new ItemCarrito();
        item2.setIdProducto(2L);
        item2.setNombre("Producto 2");
        item2.setMarca("Marca B");
        item2.setCantidad(2);
        item2.setPrecio(30.0);
        item2.setCarrito(nuevo);
        nuevo.getItems().add(item2);

        restTemplate.postForEntity("/api/v2/carrito", nuevo, String.class);

        //GET
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/carrito", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());

        List<Integer> ids = context.read("$[*].idCarrito");
        assertThat(ids).isNotEmpty();

        List<String> nombresProductos = context.read("$[*].items[*].nombre");
        assertThat(nombresProductos).contains("Producto 1", "Producto 2");
    }


    @Test
    @DirtiesContext
    public void shouldReturnCarritoById() {
        // Paso 1: Crear carrito con 1 ítem
        Carrito nuevo = new Carrito();
        ItemCarrito item = new ItemCarrito();
        item.setIdProducto(1L);
        item.setNombre("Producto 1");
        item.setMarca("Marca A");
        item.setCantidad(1);
        item.setPrecio(99.99);
        item.setCarrito(nuevo);
        nuevo.getItems().add(item);

        ResponseEntity<String> postResponse = restTemplate.postForEntity("/api/v2/carrito", nuevo, String.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Integer idCarritoCreado = JsonPath.parse(postResponse.getBody()).read("$.idCarrito", Integer.class);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/v2/carrito/" + idCarritoCreado, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(getResponse.getBody());
        int idCarrito = context.read("$.idCarrito");
        List<String> nombres = context.read("$.items[*].nombre");

        assertThat(idCarrito).isEqualTo(idCarritoCreado);
        assertThat(nombres).isNotEmpty();
    }


    @Test
    public void shouldReturnNotFoundForUnknownCarritoId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/carrito/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext context = JsonPath.parse(response.getBody());
        int status = context.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateNewCarrito() {
        // Construir carrito con 1 ítem
        Carrito nuevo = new Carrito();
        ItemCarrito item = new ItemCarrito();
        item.setIdProducto(1L);
        item.setNombre("Producto X");
        item.setMarca("Marca Z");
        item.setCantidad(2);
        item.setPrecio(100.0);
        item.setCarrito(nuevo);

        nuevo.getItems().add(item);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v2/carrito", nuevo, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);


        DocumentContext context = JsonPath.parse(response.getBody());
        Long idCarrito = context.read("$.idCarrito", Integer.class).longValue();


        assertThat(idCarrito).isNotNull();
    }

}
