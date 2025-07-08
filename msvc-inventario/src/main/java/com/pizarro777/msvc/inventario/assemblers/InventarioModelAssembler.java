package com.pizarro777.msvc.inventario.assemblers;

import com.pizarro777.msvc.inventario.controller.InventarioControllerV2;
import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component

public class InventarioModelAssembler implements RepresentationModelAssembler<InventarioDto, EntityModel<InventarioDto>> {

    @Override
    public EntityModel<InventarioDto> toModel(InventarioDto dto) {
        return EntityModel.of(
                dto,
                linkTo(methodOn(InventarioControllerV2.class).obtenerInventario(dto.getId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).listarInventarios()).withRel("inventarios"),
                Link.of("http://localhost:8005/api/v2/productos/" + dto.getIdProducto())
                        .withRel("producto"),


                Link.of("http://localhost:8007/api/v2/sucursales/" + dto.getIdSucursal())
                        .withRel("sucursal")
        );
    }
}
