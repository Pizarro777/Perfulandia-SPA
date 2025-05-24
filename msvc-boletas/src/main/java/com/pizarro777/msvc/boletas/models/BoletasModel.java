package com.pizarro777.msvc.boletas.models;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class BoletasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBoletas")
    private Long idBoletas;

    @Column(name = "nombreBoletas")
    private String nombreBoletas;

    @Column(name = "numeroBoletas")
    private int numeroBoletas;

    @Column(name = "cantidadBoletas")
    private int cantidadBoletas;

    @Column(name = "precioBoletas")
    private double precioBoletas;

}
