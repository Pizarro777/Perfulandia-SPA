package com.pizarro777.msvc.proveedor.assemblers;

import com.pizarro777.msvc.proveedor.dtos.ProveedorControllerV2;
import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>>   {

    @Override
    public EntityModel<Proveedor> toModel(Proveedor entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProveedorControllerV2.class).findById(entity.getIdProveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).findAll()).withRel("proveedor")
        );
    }
}
