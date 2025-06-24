package com.pizarro777.msvc.carrito.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    // Calcula el precio total del carrito
    public Double calcularPrecioTotal() {
        return items.stream()
                .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                .sum();
    }

    // Relación uno a muchos con ItemCarrito
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items = new ArrayList<>();

}
