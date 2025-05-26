package com.pizarro777.msvc.sucursales.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sucursales")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String ciudad;

}

