package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProveedorControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllProveedoresWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/proveedor", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int proveedoresCount = documentContext.read("$.length()");
        assertThat(proveedoresCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idProveedor");
        assertThat(ids).containsExactlyInAnyOrder(1, 2);

        JSONArray telefonos = documentContext.read("$..telefono");
        assertThat(telefonos).containsExactlyInAnyOrder(987654321, 912345678);
    }

    @Test
    public void shouldReturnAProveedorWhenFindById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/proveedor/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProveedor = documentContext.read("$.idProveedor");
        assertThat(idProveedor).isEqualTo(1);

        Integer telefono = documentContext.read("$.telefono");
        assertThat(telefono).isEqualTo(987654321);
    }

    @Test
    public void shouldReturnAProveedorWithUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/proveedor/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewProveedor() {
        Proveedor proveedor = new Proveedor(987654321, "Av. Pizarro 777", "Perfume" );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/v1/proveedor",
                proveedor,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProveedor = documentContext.read("$.idProveedor");
        assertThat(idProveedor).isEqualTo(3);

        String direccion = documentContext.read("$.direccion");
        assertThat(direccion).isEqualTo("Av. Pizarro 777");
    }

    @Test
    public void shouldReturnBadRequestWhenTelefonoIsNull() {
        Proveedor proveedorInvalido = new Proveedor(null, "Av. Principal 123", "Servicios varios");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/v1/proveedor",
                proveedorInvalido,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String message = documentContext.read("$.errors[0]");
        assertThat(message).contains("El teléfono es obligatorio");
    }

    @Test
    public void shouldReturnBadRequestWhenDireccionIsBlank() {
        Proveedor proveedorInvalido = new Proveedor(987654321, "Av. Pizarro 777", "Perfume");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/v1/proveedor",
                proveedorInvalido,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String message = documentContext.read("$.errors[0]");
        assertThat(message).contains("La dirección no puede estar vacía");
    }
}
