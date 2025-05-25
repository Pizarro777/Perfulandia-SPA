package com.pizarro777.msvc.carrito.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carrito")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;


}
