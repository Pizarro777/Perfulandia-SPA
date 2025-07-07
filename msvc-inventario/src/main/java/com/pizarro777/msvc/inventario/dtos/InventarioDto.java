package com.pizarro777.msvc.inventario.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDto {

    private Long id;
    private Long idProducto;
    private Long idSucursal;
    private Integer cantidad;
    private String direccion;

}
