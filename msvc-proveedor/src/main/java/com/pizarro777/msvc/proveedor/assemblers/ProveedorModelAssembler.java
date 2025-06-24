package com.pizarro777.msvc.proveedor.assemblers;

import com.pizarro777.msvc.proveedor.controllers.ProveedorControllerV2;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>>   {

    @Override
    public EntityModel<Proveedor> toModel(Proveedor entity) {
        // Link link = Link.of("http://localhost:8006/api/v1/producto/"+entity.getIdBoletas()).withRel("producto");
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProveedorControllerV2.class).findById(entity.getIdProveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).findAll()).withRel("proveedor")
                // Link.of("http://localhost:8006/api/v1/producto/"+entity.getIdProveedor()).withRel("producto")
        );
    }
}
