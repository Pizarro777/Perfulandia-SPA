package com.pizarro777.msvc.clientes.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String direccion;
    private String correo;
}
