package com.pizarro777.msvc.boletas.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "boletas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@Schema(description = "Entidad que representa una Boleta")
public class Boletas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    @Schema(description = "ID de la boleta", example = "1")
    private Long idBoletas;

    @Column(name = "nombre_boleta", nullable = false)
    @NotBlank(message = "El nombre de la boleta no puede estar vacío")
    @Schema(description = "Nombre de la boleta", example = "Boleta de Servicio")
    private String nombreBoletas;

    @Column(name = "precio_boleta")
    @NotNull(message = "El precio de la boleta es obligatorio")
    @Min(value = 0, message = "El precio de la boleta debe ser mayor o igual a cero")
    @Schema(description = "Precio de la boleta", example = "18000")
    private Double precioBoletas;

    public Boletas(String nombreBoletas, double precioBoletas) { // <--- El nombre del constructor coincide
        this.nombreBoletas = nombreBoletas;
        this.precioBoletas = precioBoletas;
    }


}
