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

    @Column(name = "nombre_proveedor", nullable = false)
    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Schema(description = "Nombre del proveedor", example = "Proveedor de Servicio")
    private String nombreProveedor;

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

    public Proveedor(String nombreProveedor, Integer telefono, Long idProveedor, String direccion, String servicio) {
        this.nombreProveedor = nombreProveedor;
        this.telefono = telefono;
        this.idProveedor = idProveedor;
        this.direccion = direccion;
        this.servicio = servicio;
    }

    public Proveedor(int telefono, String direccion, String servicio) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.servicio = servicio;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
}
