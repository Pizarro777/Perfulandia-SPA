package com.pizarro777.msvc.proveedor.assemblers;


import com.pizarro777.msvc.proveedor.dtos.ProveedorControllerV2;
import com.pizarro777.msvc.proveedor.dtos.ProveedorProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProveedorProductoDTOModelAssembler implements RepresentationModelAssembler<ProveedorProductoDTO, EntityModel<ProveedorProductoDTO>>{

    @Override
    public  EntityModel<ProveedorProductoDTO> toModel(ProveedorProductoDTO productoDTO) {
        return EntityModel.of(
                productoDTO,
                linkTo(methodOn(ProveedorControllerV2.class).findProveedorProductoById(productoDTO.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).findAllProveedorProductos()).withRel("productos-proveedor")
        );
    }
}
