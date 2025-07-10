package com.pizarro777.msvc.comentario.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String marca;
    private String descripcion;
    private Double precio;
    private LocalDate fechaCreacion;
    private Integer stock;
}
