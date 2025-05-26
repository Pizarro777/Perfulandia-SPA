package com.pizarro777.msvc.carrito.dtos;


import com.pizarro777.msvc.carrito.model.Carrito;
import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class CarritoDTO {

    private Carrito carrito;
    private Long idItem;
    private Long idProducto;
    private Integer cantidad;

}
