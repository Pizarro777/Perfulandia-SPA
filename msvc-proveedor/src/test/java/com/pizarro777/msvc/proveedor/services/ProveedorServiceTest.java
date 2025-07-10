package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private Proveedor proveedorPrueba;
    private List<Proveedor> proveedor = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.proveedorPrueba = new Proveedor(
                1L,
                "Perfum Alta Gama",
                987654321L,
                "Av. Pizarro 777",
                "Perfume"
        );
        Faker faker = new Faker(new Locale("es", "CL"));


        for (int i = 0; i < 100; i++) {

            Proveedor proveedorCreate = new Proveedor(
                    faker.number().numberBetween(900000000L, 999999999L),
                    faker.address().fullAddress(),
                    faker.commerce().department()
            );
            proveedorCreate.setIdProveedor((long) (i + 2));
            proveedorCreate.setNombreProveedor(faker.company().name());
            this.proveedor.add(proveedorCreate);
        }

    }

    @Test
    @DisplayName("Debo listar todos los proveedores")
    public void shouldFindAllProveedores(){
        List<Proveedor> proveedores = this.proveedor;
        proveedores.add(proveedorPrueba);
        when(proveedorRepository.findAll()).thenReturn(proveedores);

        List<Proveedor> result = proveedorService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(proveedorPrueba);
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar un proveedor")
    public void shouldFindById(){
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedorPrueba));

        Proveedor result = proveedorService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(proveedorPrueba);
        verify(proveedorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe guardar un nuevo proveedor")
    public void shouldSaveProveedor(){
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedorPrueba);

        Proveedor result = proveedorService.save(proveedorPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(proveedorPrueba);
        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }
}
