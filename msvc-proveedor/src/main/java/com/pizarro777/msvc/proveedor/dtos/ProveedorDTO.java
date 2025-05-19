package com.pizarro777.msvc.proveedor.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDTO {

    private Long idProveedor;
    private Integer telefono ;
    private String direccion;
    private String servicio;
}
