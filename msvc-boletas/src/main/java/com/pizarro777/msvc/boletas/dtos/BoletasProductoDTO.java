package com.pizarro777.msvc.boletas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoletasProductoDTO {

    private Long idBoletas;

    private Long idProducto;

    private String nombreProducto;

    private String marcaProducto;

    private String descripcion;

    private Double precio;

    private LocalDate fechaCreacion;

    private Integer stock;

}
