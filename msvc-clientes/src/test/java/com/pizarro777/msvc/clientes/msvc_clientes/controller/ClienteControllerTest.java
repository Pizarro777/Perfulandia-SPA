package com.pizarro777.msvc.clientes.msvc_clientes.controller;

import com.pizarro777.msvc.clientes.models.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Cliente cliente1 = Cliente.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .rut("12345678-9")
                .correo("juan@email.com")
                .direccion("Calle 1")
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("Maria")
                .apellido("López")
                .rut("87654321-K")
                .correo("maria@email.com")
                .direccion("Calle 2")
                .build();

        restTemplate.postForEntity("/api/v2/clientes", new HttpEntity<>(cliente1, headers), String.class);
        restTemplate.postForEntity("/api/v2/clientes", new HttpEntity<>(cliente2, headers), String.class);
    }

    @Test
    void testListarClientes() {
        ResponseEntity<Cliente[]> response = restTemplate.getForEntity("/api/v2/clientes", Cliente[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        List<Cliente> clientes = List.of(response.getBody());

        assertThat(clientes).extracting("nombre").contains("Juan", "Maria");
        assertThat(clientes).extracting("rut").contains("12345678-9", "87654321-K");
    }
}
