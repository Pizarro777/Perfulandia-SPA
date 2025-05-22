package com.pizarro777.msvc.boletas.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoletasDTO {

    private Long id;
    private String nombreBoleta;
    private int numeroBoleta;
    private int cantidadBoletas;
    private double precioBoletas;
}
