package com.pizarro777.msvc.proveedor.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "proveedores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@Schema(description = "Entidad que representa un Proveedor")
public class Proveedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    @Schema(description = "ID del proveedor", example = "1")
    private Long idProveedor;

    @NotNull(message = "El teléfono es obligatorio")
    @Column(name = "telefono", nullable = false)
    @Schema(description = "Teléfono del proveedor", example = "987654321")
    private Integer telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(name = "direccion", nullable = false)
    @Schema(description = "Dirección del proveedor", example = "Av. Siempre Viva 123")
    private String direccion;

    @NotBlank(message = "El servicio no puede estar vacío")
    @Column(name = "servicio", nullable = false)
    @Schema(description = "Servicio que provee", example = "Suministro de materiales")
    private String servicio;

    // Constructor sin id (por si quieres crear nuevo proveedor sin asignar id manualmente)
    public Proveedor(Integer telefono, String direccion, String servicio) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.servicio = servicio;
    }
}
