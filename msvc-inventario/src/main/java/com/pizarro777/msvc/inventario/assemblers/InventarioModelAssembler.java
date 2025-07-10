package com.pizarro777.msvc.inventario.assemblers;

import com.pizarro777.msvc.inventario.controller.InventarioControllerV2;
import com.pizarro777.msvc.inventario.model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(InventarioControllerV2.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withRel("inventarios"),

                Link.of("/api/v2/producto/" + entity.getIdProducto()).withRel("producto"),
                Link.of("/api/v2/sucursal/" + entity.getIdSucursal()).withRel("sucursal")
        );
    }
}
