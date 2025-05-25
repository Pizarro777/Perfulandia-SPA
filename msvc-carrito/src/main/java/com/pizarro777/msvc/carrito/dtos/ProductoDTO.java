package com.pizarro777.msvc.carrito.dtos;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String marca;
    private String descripcion;
    private Double precio;
    private Integer stock;

}
