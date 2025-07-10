package com.pizarro777.msvc.comentario.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.comentario.model.Comentario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerComentario {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllComentariosWhenListIsRequested() {
        // Crear comentario "Buen producto"
        Comentario c1 = new Comentario();
        c1.setComentario("Buen producto");
        c1.setIdProducto(1L);
        c1.setFechaCreacion(LocalDate.now());
        restTemplate.postForEntity("/api/v2/comentarios", c1, String.class);

        // Crear comentario "Excelente servicio"
        Comentario c2 = new Comentario();
        c2.setComentario("Excelente servicio");
        c2.setIdProducto(1L);
        c2.setFechaCreacion(LocalDate.now());
        restTemplate.postForEntity("/api/v2/comentarios", c2, String.class);

        // Ahora sí hacemos la petición GET
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/comentarios", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        List<String> comentarios = context.read("$._embedded.comentarioList[*].comentario");

        assertThat(comentarios).contains("Buen producto", "Excelente servicio");
    }

    @Test
    public void shouldReturnComentarioById() {
        // 1. Crear un nuevo comentario primero (POST)
        Comentario nuevo = new Comentario();
        nuevo.setComentario("Comentario para test");
        nuevo.setIdProducto(1L);
        nuevo.setFechaCreacion(LocalDate.now());

        ResponseEntity<String> postResponse = restTemplate.postForEntity("/api/v2/comentarios", nuevo, String.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // 2. Leer el idComentario del JSON de la respuesta POST
        DocumentContext postContext = JsonPath.parse(postResponse.getBody());
        Long idComentario = postContext.read("$.idComentario", Long.class);
        assertThat(idComentario).isNotNull();

        // 3. Ahora buscar ese comentario por ID con GET
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/v2/comentarios/" + idComentario, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext getContext = JsonPath.parse(getResponse.getBody());
        Long getIdComentario = getContext.read("$.idComentario", Long.class);
        String getComentario = getContext.read("$.comentario");

        assertThat(getIdComentario).isEqualTo(idComentario);
        assertThat(getComentario).isEqualTo("Comentario para test");
    }

    @Test
    public void shouldReturnNotFoundForUnknownComentarioId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/comentarios/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    public void shouldCreateNewComentario() {
        Comentario nuevo = new Comentario();
        nuevo.setComentario("Excelente servicio");
        nuevo.setIdProducto(1L);
        nuevo.setFechaCreacion(LocalDate.of(2025, 7, 9));

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v2/comentarios", nuevo, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext context = JsonPath.parse(response.getBody());
        Long idComentario = context.read("$.idComentario", Long.class);

        assertThat(idComentario).isNotNull();
    }
}
