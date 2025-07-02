package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.assertj.core.api.Assertions;
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
    public void shouldReturnAllProveedorWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/proveedor", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int proveedorCount = documentContext.read("$.content.length()");
        assertThat(proveedorCount).isGreaterThanOrEqualTo(1);

        JSONArray servicios = documentContext.read("$.content..servicio");
        assertThat(servicios).isNotEmpty();
    }

    @Test
    public void shouldReturnAProveedorWhenFindById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/proveedor/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProveedor = documentContext.read("$.content.idProveedor");
        Integer telefono = documentContext.read("$.content.telefono");
        String direccion = documentContext.read("$.content.direccion");
        String servicio = documentContext.read("$.content.servicio");

        assertThat(idProveedor).isEqualTo(1);
        assertThat(telefono).isNotNull();
        assertThat(direccion).isNotEmpty();
        assertThat(servicio).isNotEmpty();
    }

    @Test
    public void shouldReturnAProveedorWithUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/proveedor/9999", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        Assertions.assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewProveedor() {
        ProveedorModel nuevoProveedor = new ProveedorModel();
        nuevoProveedor.setTelefono(93742861);
        nuevoProveedor.setDireccion("Av. Brasil 2012");
        nuevoProveedor.setServicio("Streaming");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/proveedor", nuevoProveedor, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProveedor = documentContext.read("$.content.idProveedor");
        Integer telefono = documentContext.read("$.content.telefono");
        String direccion = documentContext.read("$.content.direccion");
        String servicio = documentContext.read("$.content.servicio");

        assertThat(idProveedor).isNotNull();
        assertThat(telefono).isEqualTo(93742861);
        assertThat(direccion).isEqualTo("Av. Brasil 2012");
        assertThat(servicio).isEqualTo("Streaming");
    }


}
