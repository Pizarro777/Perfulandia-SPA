package com.pizarro777.msvc.clientes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idCliente;

    @Column(unique = true)
    @NotBlank
    private String rut;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    private String correo;
}
