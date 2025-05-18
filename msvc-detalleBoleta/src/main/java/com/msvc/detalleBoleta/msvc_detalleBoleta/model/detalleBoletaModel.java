package com.msvc.detalleBoleta.msvc_detalleBoleta.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.aspectj.bridge.IMessage;

@Entity
@Table(name="DetalleBoletas")
@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString
public class detalleBoletaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleBoleta")
    private Long idDetalleBoleta;

    @Column(name = "precioUnitario", nullable = false)
    @NotNull(message = "El campo precio no puede ser vacio")
    private Double precioUnitario;

    @Column(name = "cantidad")
    private Integer cantidad;

}
