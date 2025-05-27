package com.pizarro777.msvc.proveedor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ProveedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Long idProveedor;

    @NotNull
    @Column(name = "telefono")
    private Integer telefono;

    @NotBlank
    @Column(name = "direccion")
    private String direccion;

    @NotBlank
    @Column(name = "servicio")
    private String servicio;

    @NotNull
    @Column(name = "idProducto")
    private Long idProducto;

    @NotNull
    @Column(name = "idBoletas")
    private Long idBoletas;
}
