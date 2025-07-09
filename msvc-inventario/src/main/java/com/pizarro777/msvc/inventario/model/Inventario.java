package com.pizarro777.msvc.inventario.model;

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

        @NotNull(message = "El id de la sucursal es obligatorio.")
        private Long idSucursal;

        @NotNull(message = "La cantidad es obligatoria.")
        @Min(value = 0, message = "La cantidad no puede ser negativa.")

        private Integer cantidad;
        @NotNull(message = "El nombre es obligatorio.")
        private String nombreProducto;
        @NotNull(message = "La direccion es obligatoria.")
        private String NombreSucursal;



        public Inventario() {}

        public Inventario(Long idProducto, Long idSucursal, Integer cantidad) {
                this.idProducto = idProducto;
                this.idSucursal = idSucursal;
                this.cantidad = cantidad;

        }
}



