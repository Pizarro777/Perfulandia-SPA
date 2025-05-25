package com.pizarro777.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name="Productos")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Producto {

    // Identificacion del Producto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    // Nombre del Producto
    @NotBlank(message = "El nombre es obligatorio.")
    @Column(nullable = false, length = 150, unique = true)
    private String nombre;

    // Marca del Producto
    @NotBlank(message = "La marca es obligatoria.")
    @Column(nullable = false, length = 150)
    private String marca;

    // Descripcion del Producto (opcional)
    @Size(max = 255, min = 0, message = "La cantidad de caracteres no pueden superar los 255.")
    @Column(nullable = true, length = 255)
    private String descripcion;

    // Precio del Producto
    @Positive
    @Column(nullable = false)
    private Double precio;

    // Stock Disponible (puede ser 0)
    @PositiveOrZero
    @Column(nullable = false)
    private Integer stock;

    // Manejo de versiones del producto
    @Version
    private Integer version;

}
