package com.pizarro777.msvc.productos.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        // Crear producto 1
        ProductoInputDTO p1 = ProductoInputDTO.builder()
                .nombre("Awesome Silk Knife")
                .marca("MarcaTest1")
                .descripcion("Descripción 1")
                .precio(10.0)
                .stock(5)
                .build();

        // Crear producto 2
        ProductoInputDTO p2 = ProductoInputDTO.builder()
                .nombre("Fantastic Cotton Pants")
                .marca("MarcaTest2")
                .descripcion("Descripción 2")
                .precio(20.0)
                .stock(3)
                .build();

        // Encabezados para JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Enviar peticiones POST
        restTemplate.postForEntity("/api/v2/productos", new HttpEntity<>(p1, headers), String.class);
        restTemplate.postForEntity("/api/v2/productos", new HttpEntity<>(p2, headers), String.class);
    }

    @Test
    public void DeberiaDevolverTodosLosProductosCuandoListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/productos", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        List<Integer> ids = context.read("$._embedded.productoList[*].id");
        List<String> nombres = context.read("$._embedded.productoList[*].nombre");

        System.out.println("Productos actuales: " + nombres);

        Assertions.assertThat(ids).isNotEmpty();
        Assertions.assertThat(nombres).contains("Awesome Silk Knife", "Fantastic Cotton Pants");
    }
}


