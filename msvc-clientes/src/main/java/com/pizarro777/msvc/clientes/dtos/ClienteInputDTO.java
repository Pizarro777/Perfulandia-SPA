package com.pizarro777.msvc.clientes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteInputDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    private String apellido;

    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato debe ser XXXXXXXX-X")
    private String rut;

    private String direccion;

    @Email(message = "Debe ser un correo electronico valido.")
    private String correo;
}
