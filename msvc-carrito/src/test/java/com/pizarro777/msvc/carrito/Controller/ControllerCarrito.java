package com.pizarro777.msvc.carrito.Controller;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.model.ItemCarrito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerCarrito {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCarritoList() {
        // Realiza la solicitud al endpoint correcto
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/carrito", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Analiza el JSON devuelto
        DocumentContext context = JsonPath.parse(response.getBody());

        // Extrae la lista de ID de carritos
        List<Integer> ids = context.read("$._embedded.carritoList[*].idCarrito");
        assertThat(ids).isNotEmpty();

        // Extrae los nombres de productos dentro de los ítems de los carritos
        List<String> nombresProductos = context.read("$._embedded.carritoList[*].items[*].nombre");

        // Valida que existan productos con nombres esperados
        assertThat(nombresProductos).contains("Producto 1", "Producto 2");
    }

    @Test
    public void shouldReturnCarritoById() {
        // Cambia el ID por uno válido en tu base de datos precargada
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/carrito/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        int idCarrito = context.read("$.idCarrito");
        List<String> nombres = context.read("$.items[*].nombre");

        assertThat(idCarrito).isEqualTo(1);
        assertThat(nombres).contains("Producto 1", "Producto 2"); // ajusta según tus datos
    }

    @Test
    public void shouldReturnNotFoundForUnknownCarritoId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v2/carrito/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext context = JsonPath.parse(response.getBody());
        int status = context.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateNewCarrito() {
        // Construir carrito con 1 ítem
        Carrito nuevo = new Carrito();
        ItemCarrito item = new ItemCarrito();
        item.setIdProducto(1L);
        item.setNombre("Producto X");
        item.setMarca("Marca Z");
        item.setCantidad(2);
        item.setPrecio(100.0);
        item.setCarrito(nuevo);

        nuevo.getItems().add(item);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v2/carrito", nuevo, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        Long idCarrito = context.read("$.idCarrito");

        assertThat(idCarrito).isNotNull();
    }

}
