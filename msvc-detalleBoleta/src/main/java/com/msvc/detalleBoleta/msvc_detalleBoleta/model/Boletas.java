package com.msvc.detalleBoleta.msvc_detalleBoleta.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Boletas {

    private Long idBoletas;
    private String numeroBoleta;
    private Integer cantidadBoletas;
    private double precioBoletas;

}
