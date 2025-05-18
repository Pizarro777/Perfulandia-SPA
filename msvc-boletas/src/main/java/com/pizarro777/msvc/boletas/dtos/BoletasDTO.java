package com.pizarro777.msvc.boletas.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoletasDTO {

    private String numeroBoleta;
    private int cantidadBoletas;
    private double precioBoletas;
}
