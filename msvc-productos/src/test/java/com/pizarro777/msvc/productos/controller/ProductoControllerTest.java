package com.pizarro777.msvc.productos.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllProductosWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/productos", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        List<Integer> ids = context.read("$._embedded.productoList[*].id");
        List<String> productos = context.read("$._embedded.productoList[*].productos");

        Assertions.assertThat(ids).isNotEmpty();
        Assertions.assertThat(productos).contains("Buen producto", "Excelente servicio");
    }

}
