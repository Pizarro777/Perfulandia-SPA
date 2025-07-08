package com.pizarro777.msvc.sucursales.service;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import com.pizarro777.msvc.sucursales.services.SucursalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SucursalServiceTest {

    private SucursalRepository repository;
    private SucursalService service;

    @BeforeEach
    public void setUp() {
        repository = mock(SucursalRepository.class);
        service = new SucursalService(repository);
    }

    @Test
    public void testCrearSucursal() {
        Sucursal sucursal = new Sucursal(null, "Sucursal A", "Av. Siempre Viva", "Springfield");
        Sucursal guardada = new Sucursal(1L, "Sucursal A", "Av. Siempre Viva", "Springfield");

        when(repository.save(sucursal)).thenReturn(guardada);

        Sucursal resultado = service.crearSucursal(sucursal);

        assertNotNull(resultado.getId());
        assertEquals("Sucursal A", resultado.getNombre());
        verify(repository).save(sucursal);
    }

    @Test
    public void testObtenerPorIdExistente() {
        Sucursal sucursal = new Sucursal(1L, "Sucursal B", "Calle Falsa", "Ciudad Gótica");
        when(repository.findById(1L)).thenReturn(Optional.of(sucursal));

        Sucursal resultado = service.obtenerPorId(1L);

        assertEquals("Sucursal B", resultado.getNombre());
        verify(repository).findById(1L);
    }

    @Test
    public void testObtenerPorIdNoExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.obtenerPorId(99L);
        });

        assertTrue(ex.getMessage().contains("No se encontró el id"));
    }

    @Test
    public void testListarTodas() {
        List<Sucursal> lista = Arrays.asList(
                new Sucursal(1L, "Sucursal 1", "Dir 1", "Ciudad 1"),
                new Sucursal(2L, "Sucursal 2", "Dir 2", "Ciudad 2")
        );
        when(repository.findAll()).thenReturn(lista);

        List<Sucursal> resultado = service.listarTodas();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    public void testEliminarSucursal() {
        doNothing().when(repository).deleteById(1L);
        service.eliminarSucursal(1L);
        verify(repository).deleteById(1L);
    }
}

