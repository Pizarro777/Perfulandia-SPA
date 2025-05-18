package com.msvc.detalleBoleta.msvc_detalleBoleta.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private Boolean activo;
}
