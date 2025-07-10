package com.pizarro777.msvc.proveedor.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    ProveedorRepository proveedorRepository;


    @BeforeEach
    void setUp() {
        proveedorRepository.deleteAll();

        proveedorRepository.save(new Proveedor(null,"Proveedor Juan", 987654321L, "Av. Principal 123", "Servicio Express"));
        proveedorRepository.save(new Proveedor(null,"Proveedor Pedro", 965732864L, "Calle Falsa 456", "Servicio Normal"));
    }

    @Test
    public void shouldReturnAllProveedoresWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/proveedor", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int proveedoresCount = documentContext.read("$.length()");
        assertThat(proveedoresCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idProveedor");
        assertThat(ids).hasSize(2);

        JSONArray nombres = documentContext.read("$..nombreProveedor");
        assertThat(nombres).containsExactlyInAnyOrder("Proveedor Juan", "Proveedor Pedro");

        JSONArray telefonos = documentContext.read("$..telefono");
        assertThat(telefonos).containsExactlyInAnyOrder(987654321, 965732864);

        JSONArray direcciones = documentContext.read("$..direccion");
        assertThat(direcciones).containsExactlyInAnyOrder("Av. Principal 123", "Calle Falsa 456");

        JSONArray servicios = documentContext.read("$..servicio");
        assertThat(servicios).containsExactlyInAnyOrder("Servicio Express", "Servicio Normal");
    }

    @Test
    public void shouldReturnAnProveedorWhenFindById(){
        Proveedor guardado = proveedorRepository.save(
                new Proveedor(null, "Proveedor Juan", 987654321L, "Av. Principal 123", "Servicio Express")
        );

        ResponseEntity<String> response = restTemplate.getForEntity("/api/proveedor/" + guardado.getIdProveedor(), String.class);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewProveedor(){

        Proveedor nuevoProveedor = new Proveedor(null, "Proveedor Nuevo", 99998888L, "Dirección Nueva", "Servicio Premium");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/proveedor", nuevoProveedor, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        Number idProveedor = documentContext.read("$.idProveedor");

        assertThat(idProveedor).isNotNull();

        String nombreProveedor = documentContext.read("$.nombreProveedor");
        assertThat(nombreProveedor).isEqualTo("Proveedor Nuevo");

        assertThat(proveedorRepository.findById(idProveedor.longValue())).isPresent();
    }

}
