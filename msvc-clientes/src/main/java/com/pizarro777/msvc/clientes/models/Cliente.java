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

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @Column(nullable = true)
    private String apellido;

    @Column(unique = true)
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato debe ser XXXXXXXX-X")
    @NotNull(message = "Este campo es obligatorio.")
    private String rut;

    @Column(nullable = true)
    private String direccion;

    @Column(unique = true, nullable = false)
    @Email(message = "Debe ser un correo electronico valido.")
    private String correo;
}