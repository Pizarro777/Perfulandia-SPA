package com.pizarro777.msvc.carrito.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito")
    private List<ItemCarrito> items;

}
