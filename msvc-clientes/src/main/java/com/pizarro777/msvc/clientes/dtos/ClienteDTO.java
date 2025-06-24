package com.pizarro777.msvc.clientes.dtos;


import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ClienteDTO {

    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
}
