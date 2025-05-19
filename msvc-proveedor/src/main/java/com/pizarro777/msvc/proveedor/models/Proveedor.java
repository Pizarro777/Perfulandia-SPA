package com.pizarro777.msvc.proveedor.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Proveedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Proveedor {

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
}
