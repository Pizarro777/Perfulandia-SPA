package com.pizarro777.msvc.proveedor.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ProveedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Long idProveedor;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "servicio")
    private String servicio;

    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "idBoletas")
    private Long idBoletas;
}
