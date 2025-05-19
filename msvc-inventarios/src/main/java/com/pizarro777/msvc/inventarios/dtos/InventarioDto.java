package com.pizarro777.msvc.inventarios.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventarioDto {

    private Long idProducto;
    private Integer cantidad;
    private String ubicacion;

    public InventarioDto() {
    }

    public InventarioDto(Long idProducto, Integer cantidad, String ubicacion) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
    }
}
