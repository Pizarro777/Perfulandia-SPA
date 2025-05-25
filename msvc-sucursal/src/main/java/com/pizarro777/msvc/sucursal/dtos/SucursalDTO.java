package com.pizarro777.msvc.sucursal.dtos;

import lombok.*;

@Getter
@Setter @ToString
@AllArgsConstructor @NoArgsConstructor


public class SucursalDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private Boolean activo = true;
}

