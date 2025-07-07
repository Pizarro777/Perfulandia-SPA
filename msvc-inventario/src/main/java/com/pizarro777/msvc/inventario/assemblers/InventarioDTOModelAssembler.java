package com.pizarro777.msvc.inventario.assemblers;

import org.springframework.lang.NonNull;


import com.pizarro777.msvc.inventario.controller.InventarioControllerV2;
import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioDTOModelAssembler implements RepresentationModelAssembler<InventarioDto, EntityModel<InventarioDto>> {

    @Override
    @NonNull
    public EntityModel<InventarioDto> toModel(@NonNull InventarioDto dto) {
        return EntityModel.of(
                dto,
                // Enlace al recurso actual
                linkTo(methodOn(InventarioControllerV2.class).obtenerInventario(dto.getId())).withSelfRel(),

                // Enlace a todos los inventarios
                linkTo(methodOn(InventarioControllerV2.class).listarInventarios()).withRel("inventarios")
        );
    }
}
