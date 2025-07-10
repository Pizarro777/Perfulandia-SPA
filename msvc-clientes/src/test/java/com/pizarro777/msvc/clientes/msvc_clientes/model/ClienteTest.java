package com.pizarro777.msvc.clientes.msvc_clientes.model;

import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.repositories.ClienteRepository;
import com.pizarro777.msvc.clientes.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setRut("11.111.111-1");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setCorreo("juan@example.com");
    }

    @Test
    void testCrearCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.crearCliente(cliente);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testObtenerPorId_Existe() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.obtenerPorId(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(clienteRepository).findById(1L);
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.obtenerPorId(2L);
        });

        assertEquals("No se encontro el id en la base de datos: 2", exception.getMessage());
        verify(clienteRepository).findById(2L);
    }

    @Test
    void testListarTodos() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));

        List<Cliente> clientes = clienteService.listarTodos();

        assertEquals(1, clientes.size());
        assertEquals("Juan", clientes.get(0).getNombre());
        verify(clienteRepository).findAll();
    }

    @Test
    void testActualizarCliente() {
        Cliente datosActualizados = new Cliente();
        datosActualizados.setNombre("Carlos");
        datosActualizados.setApellido("Gómez");
        datosActualizados.setRut("22.222.222-2");
        datosActualizados.setCorreo("carlos@example.com");
        datosActualizados.setDireccion("Nueva dirección");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cliente actualizado = clienteService.actualizarCliente(1L, datosActualizados);

        assertEquals("Carlos", actualizado.getNombre());
        assertEquals("Gómez", actualizado.getApellido());
        assertEquals("22.222.222-2", actualizado.getRut());
        assertEquals("carlos@example.com", actualizado.getCorreo());
        assertEquals("Nueva dirección", actualizado.getDireccion());

        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void testEliminar() {
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.eliminar(1L);

        verify(clienteRepository).deleteById(1L);
    }
}
