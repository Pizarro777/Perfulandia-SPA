package com.pizarro777.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
*
* @author Pizarro777
* */
@Entity
@Table(name="productos")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Producto {

    /*
     * Seleccion de los atrubutos de la clase, se utuliza @ID para decirle al programa
     * cual es el id y que este no se pueda repetir
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idProducto;

    /* Se utiliza para evitar que se ingresen datos en blanco */
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;


    @Size(max = 255, min = 0, message = "La cantidad de caracteres no pueden superar los 255.")
    private String descripcion;

    private Double precio;

    private Boolean activo;

}
