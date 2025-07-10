package com.pizarro777.msvc.clientes.dtos;

import lombok.Data;
import java.util.List;

@Data
public class CarritoDTO {

        private Long id;
        private Long usuarioId;
        private List<ProductoOutputDTO> productos;

}

