package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.exceptions.BoletasException;
import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoletasServiceTest {

    @Mock
    private BoletasRepository boletasRepository;

    @InjectMocks
    private BoletaServiceImpl boletasService;

    private Boletas boletaPrueba;
    private List<Boletas> boletasList;

    @BeforeEach
    public void setUp() {
        boletaPrueba = new Boletas();
        boletaPrueba.setIdBoletas(1L);
        boletaPrueba.setNombreBoletas("Boleta de ejemplo");
        boletaPrueba.setPrecioBoletas(1000.0);

        boletasList = new ArrayList<>();
        for (long i = 2; i <= 101; i++) {
            Boletas boleta = new Boletas();
            boleta.setIdBoletas(i);
            boleta.setNombreBoletas("Boleta " + i);
            boleta.setPrecioBoletas(500.0 + i);
            boletasList.add(boleta);
        }
    }

    @Test
    @DisplayName("Debe listar todas las boletas")
    public void shouldFindAllBoletas() {
        List<Boletas> todas = new ArrayList<>(boletasList);
        todas.add(boletaPrueba);

        when(boletasRepository.findAll()).thenReturn(todas);

        List<Boletas> result = boletasService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(boletaPrueba);
        verify(boletasRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe encontrar una boleta por ID")
    public void shouldFindById() {
        when(boletasRepository.findById(1L)).thenReturn(Optional.of(boletaPrueba));

        Boletas result = boletasService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaPrueba);
        verify(boletasRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la boleta no existe")
    public void shouldNotFindBoletasById() {
        Long idInexistente = 999L;
        when(boletasRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            boletasService.findById(idInexistente);
        }).isInstanceOf(BoletasException.class)
                .hasMessageContaining("La boleta con id " + idInexistente + " no se encuentra en la base de datos");

        verify(boletasRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva boleta")
    public void shouldSaveBoletas() {
        when(boletasRepository.save(any(Boletas.class))).thenReturn(boletaPrueba);

        Boletas result = boletasService.save(boletaPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaPrueba);
        verify(boletasRepository, times(1)).save(any(Boletas.class));
    }

}
