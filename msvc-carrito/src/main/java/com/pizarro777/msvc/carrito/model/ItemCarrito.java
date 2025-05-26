package com.pizarro777.msvc.carrito.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_carrito")
public class ItemCarrito {

    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(name = "id_producto",nullable = false)
    private Long idProducto;

    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    @Column(name = "precio", nullable = false)
    private Double precio;


}
