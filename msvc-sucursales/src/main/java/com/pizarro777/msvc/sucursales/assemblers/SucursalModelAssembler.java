package com.pizarro777.msvc.sucursales.assemblers;

import com.pizarro777.msvc.sucursales.controllers.SucursalController;
import com.pizarro777.msvc.sucursales.models.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {

    @Override
    public EntityModel<Sucursal> toModel(Sucursal entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(SucursalController.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(SucursalController.class).findAll()).withRel("sucursales")
        );
    }
}