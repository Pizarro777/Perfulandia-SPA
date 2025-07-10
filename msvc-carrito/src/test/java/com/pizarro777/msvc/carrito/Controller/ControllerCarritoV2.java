package com.pizarro777.msvc.carrito.Controller;


import com.pizarro777.msvc.carrito.assemblers.CarritoModelAssembler;
import com.pizarro777.msvc.carrito.controller.CarritoControllerV2;
import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.service.CarritoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerCarritoV2 {

    @Mock
    private CarritoService carritoService;

    @Mock
    private CarritoModelAssembler carritoModelAssembler;

    @InjectMocks
    private CarritoControllerV2 carritoController;

    @Test
    void findById_shouldReturnCarrito_whenExists() {
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);

        when(carritoService.findById(1L)).thenReturn(carrito);
        when(carritoModelAssembler.toModel(carrito)).thenReturn(EntityModel.of(carrito));

        ResponseEntity<EntityModel<Carrito>> response = carritoController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carrito, response.getBody().getContent());
    }

    @Test
    void findById_shouldReturn404_whenNotFound() {
        when(carritoService.findById(1L)).thenReturn(null);

        ResponseEntity<EntityModel<Carrito>> response = carritoController.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
