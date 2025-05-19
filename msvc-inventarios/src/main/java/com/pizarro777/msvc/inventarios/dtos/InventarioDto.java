package com.pizarro777.msvc.inventarios.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDto {

    private Long idProducto;
    private Integer cantidad;
    private String ubicacion;


}
