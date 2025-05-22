package com.pizarro777.msvc.clientes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Este campo es obligatorio.")
    private String rut;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
}
