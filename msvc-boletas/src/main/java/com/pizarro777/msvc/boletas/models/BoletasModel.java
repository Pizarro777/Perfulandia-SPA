package com.pizarro777.msvc.boletas.models;


import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class BoletasModel {
    private Long idBoletas;
    private String numeroBoleta;
    private int cantidadBoletas;
    private double precioBoletas;

}
