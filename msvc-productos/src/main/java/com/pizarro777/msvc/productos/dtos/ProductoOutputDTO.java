package com.pizarro777.msvc.productos.dtos;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

//Datos mostrados para el Cliente
@Data
@Builder
public class ProductoOutputDTO {

    // Identificacion del Producto
    private Long id;

    // Nombre del Producto
    private String nombre;

    // Marca del Producto
    private String marca;

    // Descripcion del Producto (opcional)
    private String descripcion;

    // Precio del Producto
    private Double precio;

    //fecha creacion
    private LocalDate fechaCreacion;

    // Stock Disponible (puede ser 0)
    private Integer stock;

}
