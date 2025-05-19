package com.pizarro777.msvc.inventarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
@Entity
@Table(name = "inventarios")
public class Inventario {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "El id del producto es obligatorio.")
        private Long idProducto;

        @NotNull(message = "La cantidad es obligatoria.")
        @Min(value = 0, message = "La cantidad no puede ser negativa.")
        private Integer cantidad;

        private String ubicacion;

        public Inventario() {
        }
        public Inventario(Long idProducto, Integer cantidad, String ubicacion) {
                this.idProducto = idProducto;
                this.cantidad = cantidad;
                this.ubicacion = ubicacion;
        }

    }



