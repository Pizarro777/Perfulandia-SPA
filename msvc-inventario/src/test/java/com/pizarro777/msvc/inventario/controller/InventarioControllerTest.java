package com.pizarro777.msvc.inventario.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.inventario.model.Inventario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventarioControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetInventarioList() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/inventario", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());

        List<Integer> ids = context.read("$._embedded.inventarios[*].id");
        assertThat(ids).isNotEmpty();

        List<String> nombresProducto = context.read("$._embedded.inventarios[*].nombreProducto");
        assertThat(nombresProducto).isNotEmpty();

        List<String> nombresSucursal = context.read("$._embedded.inventarios[*].nombreSucursal");
        assertThat(nombresSucursal).isNotEmpty();
    }

    @Test
    public void shouldReturnInventarioById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/inventario/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());

        int id = context.read("$.id");
        String nombreProducto = context.read("$.nombreProducto");
        String nombreSucursal = context.read("$.nombreSucursal");

        assertThat(id).isEqualTo(1);
        assertThat(nombreProducto).isNotBlank();
        assertThat(nombreSucursal).isNotBlank();
    }

    @Test
    public void shouldReturnNotFoundForUnknownInventarioId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/inventario/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext context = JsonPath.parse(response.getBody());
        int status = context.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateNewInventario() {
        Inventario nuevo = new Inventario();
        nuevo.setIdProducto(1L);
        nuevo.setIdSucursal(1L);
        nuevo.setCantidad(50);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v2/inventario", nuevo, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext context = JsonPath.parse(response.getBody());
        Integer id = context.read("$.id");

        assertThat(id).isNotNull();
    }
}
