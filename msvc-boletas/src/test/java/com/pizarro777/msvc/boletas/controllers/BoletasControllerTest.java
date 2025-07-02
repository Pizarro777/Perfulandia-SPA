package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.models.BoletasModel;
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
public class BoletasControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllBoletasWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/boletas", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int boletasCount = documentContext.read("$.content.length()");
        assertThat(boletasCount).isGreaterThanOrEqualTo(1);

        JSONArray nombreBoletas = documentContext.read("$.content..nombreBoleta");
        assertThat(nombreBoletas).isNotEmpty();
    }

    @Test
    public void shouldReturnABoletaWhenFindById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/boletas/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idBoletas = documentContext.read("$.content.idBoletas");
        String nombreBoletas = documentContext.read("$.content.nombreBoletas");
        Double precioBoletas = documentContext.read("$.content.precioBoletas");

        assertThat(idBoletas).isEqualTo(1);
        assertThat(nombreBoletas).isNotEmpty();
        assertThat(precioBoletas).isNotNull();
    }

    @Test
    public void shouldReturnAnBoletasWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/boletas/9999", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        Assertions.assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewBoletas() {
        BoletasModel nuevaBoleta = new BoletasModel();
        nuevaBoleta.setNombreBoletas("Perfume");
        nuevaBoleta.setPrecioBoletas(25000);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v2/boletas", nuevaBoleta, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idBoletas = documentContext.read("$.content.idBoletas");
        String nombreBoletas = documentContext.read("$.content.nombreBoletas");
        Double precioBoletas = documentContext.read("$.content.precioBoletas");

        assertThat(idBoletas).isNotNull();
        assertThat(nombreBoletas).isEqualTo("Perfume");
        assertThat(precioBoletas).isEqualTo(25000);
    }

}
