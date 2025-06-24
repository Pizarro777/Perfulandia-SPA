package com.pizarro777.msvc.boletas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class BoletasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBoletas")
    private Long idBoletas;

    @NotBlank
    @Column(name = "nombreBoletas")
    private String nombreBoletas;

    @Column(name = "cantidadBoletas")
    private int cantidadBoletas;

    @Column(name = "precioBoletas")
    private double precioBoletas;

}
