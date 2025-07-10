package com.pizarro777.msvc.sucursales.assemblers;

import com.pizarro777.msvc.sucursales.controllers.SucursalController;
import com.pizarro777.msvc.sucursales.dtos.SucursalDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<SucursalDTO, EntityModel<SucursalDTO>> {

    @Override
    public EntityModel<SucursalDTO> toModel(SucursalDTO sucursal) {
        return EntityModel.of(
                sucursal,
                linkTo(methodOn(SucursalController.class).findById(sucursal.getId())).withSelfRel(),
                linkTo(methodOn(SucursalController.class).findAll()).withRel("sucursales")
        );
    }
}