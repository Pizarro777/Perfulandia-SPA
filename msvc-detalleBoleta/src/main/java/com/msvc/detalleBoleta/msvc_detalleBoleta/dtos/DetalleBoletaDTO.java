package com.msvc.detalleBoleta.msvc_detalleBoleta.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetalleBoletaDTO {

    private Long idDetalleBoleta;
    private Long cantidad;
    private Double precioUnitario;

}
