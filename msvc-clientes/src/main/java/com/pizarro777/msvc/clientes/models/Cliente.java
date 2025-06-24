package com.pizarro777.msvc.clientes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name="Cliente")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    // El nombre no puede quedar vacio
    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    // El apellido puede quedar vacio.
    @Column(nullable = true)
    private String apellido;

    // El rut es unico.
    @Column(unique = true)
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato debe ser XXXXXXXX-X")
    @NotNull(message = "Este campo es obligatorio.")
    private String rut;

    // La Direccion no puede quedar vacia.
    @Column(nullable = true)
    private String direccion;

    // El Correo es unico y puede quedar vacio.
    @Column(unique = true, nullable = false)
    @Email(message = "Debe ser un correo electronico valido.")
    private String correo;

    // Manejo de versiones del producto
    @Version
    private Integer version;
}