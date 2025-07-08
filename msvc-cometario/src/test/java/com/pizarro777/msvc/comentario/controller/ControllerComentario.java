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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerComentario {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllComentariosWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/comentarios", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        List<Integer> ids = context.read("$._embedded.comentarioList[*].idComentario");
        List<String> comentarios = context.read("$._embedded.comentarioList[*].comentario");

        assertThat(ids).isNotEmpty();
        assertThat(comentarios).contains("Buen producto", "Excelente servicio");
    }

    @Test
    public void shouldReturnComentarioById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/comentarios/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        int idComentario = context.read("$.idComentario");
        String comentario = context.read("$.comentario");

        assertThat(idComentario).isEqualTo(1);
        assertThat(comentario).isEqualTo("Buen producto");
    }

    @Test
    public void shouldReturnNotFoundForUnknownComentarioId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/comentarios/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext context = JsonPath.parse(response.getBody());
        int status = context.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateNewComentario() {
        Comentario nuevo = new Comentario();
        nuevo.setComentario("Excelente servicio");
        nuevo.setIdProducto(1L);
        nuevo.setFechaCreacion(LocalDateTime.now());

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v2/comentarios", nuevo, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        Long idComentario = context.read("$.idComentario");

        assertThat(idComentario).isNotNull();
    }
}
