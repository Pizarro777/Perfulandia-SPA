package com.pizarro777.msvc.sucursales.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizarro777.msvc.sucursales.controllers.SucursalController;
import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.services.SucursalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SucursalController.class)
class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SucursalService sucursalService;

    private Sucursal sucursal;

    @BeforeEach
    void setUp() {
        sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal Centro");
        sucursal.setDireccion("Calle Falsa 123");
        sucursal.setCiudad("Santiago");
    }

    @Test
    void shouldReturnAllSucursales() throws Exception {
        Mockito.when(sucursalService.findAll()).thenReturn(Arrays.asList(sucursal));

        mockMvc.perform(get("/api/sucursales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sucursal.getId()))
                .andExpect(jsonPath("$[0].nombre").value(sucursal.getNombre()));
    }

    @Test
    void shouldReturnNoContentIfListEmpty() throws Exception {
        Mockito.when(sucursalService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/sucursales"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnSucursalById() throws Exception {
        Mockito.when(sucursalService.findById(1L)).thenReturn(sucursal);

        mockMvc.perform(get("/api/sucursales/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Sucursal Centro"));
    }

    @Test
    void shouldReturnNotFoundIfSucursalNotExist() throws Exception {
        Mockito.when(sucursalService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/sucursales/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateSucursal() throws Exception {
        Mockito.when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(post("/api/sucursales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sucursal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Sucursal Centro"));
    }

    @Test
    void shouldUpdateSucursal() throws Exception {
        Mockito.when(sucursalService.actualizarSucursal(eq(1L), any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(put("/api/sucursales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sucursal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ciudad").value("Santiago"));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistentSucursal() throws Exception {
        Mockito.when(sucursalService.actualizarSucursal(eq(1L), any(Sucursal.class))).thenReturn(null);

        mockMvc.perform(put("/api/sucursales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sucursal)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteSucursal() throws Exception {
        Mockito.when(sucursalService.findById(1L)).thenReturn(sucursal);
        Mockito.doNothing().when(sucursalService).eliminarSucursal(1L);

        mockMvc.perform(delete("/api/sucursales/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingNonExistentSucursal() throws Exception {
        Mockito.when(sucursalService.findById(99L)).thenReturn(null);

        mockMvc.perform(delete("/api/sucursales/99"))
                .andExpect(status().isNotFound());
    }
}
