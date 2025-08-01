package com.pizarro777.msvc.proveedor.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProveedorProductoDTO {

    private Long idProveedor;

    private Long idProducto;

    private String nombreProducto;

    private String marcaProducto;

    private String descripcion;

    private Double precio;

    private Integer stock;

    private LocalDate fechaCreacion;

    private Integer version;

}
