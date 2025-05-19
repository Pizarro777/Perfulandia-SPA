package com.pizarro777.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    /* Se utiliza para evitar que se ingresen datos en blanco */
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @Size(max = 255, min = 0, message = "La cantidad de caracteres no pueden superar los 255.")
    private String descripcion;

    @Positive
    private Double precio;

    @PositiveOrZero
    private Integer stock;

    private Boolean activo ;

}
