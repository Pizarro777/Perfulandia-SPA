package com.pizarro777.msvc.boletas.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoletasControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    BoletasRepository boletasRepository;

    @BeforeEach
    void setUp() {
        boletasRepository.deleteAll();

        boletasRepository.save(new Boletas("Boleta de Perfum Alta Gama", 18000.0));
        boletasRepository.save(new Boletas("Boleta de Perfum Baja Gama", 14000.0));
    }

    @Test
    public void shouldReturnAllBoletasWhenListIsRequested(){

        ResponseEntity<String> response = restTemplate.getForEntity("/api/boletas", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int boletasCount = documentContext.read("$.length()");
        assertThat(boletasCount).isEqualTo(2);

        JSONArray names = documentContext.read("$..nombreBoletas");
        assertThat(names).containsExactlyInAnyOrder("Boleta de Perfum Alta Gama", "Boleta de Perfum Baja Gama");

        JSONArray prices = documentContext.read("$..precioBoletas");
        assertThat(prices).containsExactlyInAnyOrder(18000.0, 14000.0);
    }


    @Test
    public void shouldReturnAnBoletaWhenFindById(){

        Boletas boletaGuardada = boletasRepository.save(new Boletas("Boleta de Perfum Alta Gama", 18000.0));

        ResponseEntity<String> response = restTemplate.getForEntity("/api/boletas/" + boletaGuardada.getIdBoletas(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        Number idBoletasFromResponse = documentContext.read("$.idBoletas");
        assertThat(idBoletasFromResponse.longValue()).isEqualTo(boletaGuardada.getIdBoletas());

        String nombreBoletasFromResponse = documentContext.read("$.nombreBoletas");
        assertThat(nombreBoletasFromResponse).isEqualTo("Boleta de Perfum Alta Gama");

        Double precioBoletasFromResponse = documentContext.read("$.precioBoletas");
        assertThat(precioBoletasFromResponse).isEqualTo(18000.0);
    }

    @Test
    public void shouldReturnNotFoundWhenBoletaWithUnknownId(){

        ResponseEntity<String> response = restTemplate.getForEntity("/api/boletas/9999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        try {
            DocumentContext documentContext = JsonPath.parse(response.getBody());
            Number status = documentContext.read("$.status");
            assertThat(status).isEqualTo(404);
        } catch (Exception e) {

            System.err.println("Advertencia: No se pudo parsear el cuerpo de error como JSON con 'status' o el cuerpo está vacío. Error: " + e.getMessage());
        }
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewBoleta(){

        Boletas newBoleta = new Boletas("Boleta de Perfum Nuevo", 20000.0); // Corregido el nombre para la aserción

        ResponseEntity<String> response = restTemplate.postForEntity("/api/boletas", newBoleta, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String nombreBoletas = documentContext.read("$.nombreBoletas");
        assertThat(nombreBoletas).isEqualTo("Boleta de Perfum Nuevo");

        Double precioBoletas = documentContext.read("$.precioBoletas");
        assertThat(precioBoletas).isEqualTo(20000.0);

        Number idBoletas = documentContext.read("$.idBoletas");

        assertThat(boletasRepository.findById(idBoletas.longValue())).isPresent();
    }

    @Test
    @DirtiesContext
    public void shouldDeleteBoleta() {

        Boletas boletaAEliminar = boletasRepository.save(new Boletas("Boleta para Eliminar", 10000.0));


        assertThat(boletasRepository.findById(boletaAEliminar.getIdBoletas())).isPresent();


        ResponseEntity<Void> response = restTemplate.exchange(
                "/api/boletas/" + boletaAEliminar.getIdBoletas(),
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThat(boletasRepository.findById(boletaAEliminar.getIdBoletas())).isNotPresent();
    }



}
