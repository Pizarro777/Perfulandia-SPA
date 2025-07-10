package com.pizarro777.msvc.sucursales.service;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import com.pizarro777.msvc.sucursales.services.SucursalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    @Test
    void testGuardarSucursal() {
        // Arrange
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal Test");
        sucursal.setDireccion("Calle Test 123");
        sucursal.setCiudad("Ciudad Test");

        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursal);

        // Act
        Sucursal resultado = sucursalService.save(sucursal);

        // Assert
        assertNotNull(resultado);
        assertEquals("Sucursal Test", resultado.getNombre());
        assertEquals("Calle Test 123", resultado.getDireccion());
        assertEquals("Ciudad Test", resultado.getCiudad());
        verify(sucursalRepository).save(sucursal);
    }

    @Test
    void testFindByIdExists() {
        // Arrange
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal Existente");

        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));

        // Act
        Sucursal resultado = sucursalService.findById(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Sucursal Existente", resultado.getNombre());
        verify(sucursalRepository).findById(1L);
    }

    @Test
    void testFindByIdNotExists() {
        // Arrange
        when(sucursalRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Sucursal resultado = sucursalService.findById(99L);

        // Assert
        assertNull(resultado);
        verify(sucursalRepository).findById(99L);
    }
}
