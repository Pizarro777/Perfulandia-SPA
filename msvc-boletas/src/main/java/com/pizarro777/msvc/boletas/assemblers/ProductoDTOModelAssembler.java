package com.pizarro777.msvc.boletas.assemblers;

import com.pizarro777.msvc.boletas.dtos.ProductoDTO;
import com.pizarro777.msvc.boletas.controllers.BoletasControllerV2;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class ProductoDTOModelAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>>{

    @Override
    public EntityModel<Boletas> toModel(Boletas entity) {
        // Link link = Link.of("http://localhost:8005/api/v1/producto/"+entity.getIdBoletas()).withRel("producto");
        return EntityModel.of(
                entity,
                linkTo(methodOn(BoletasControllerV2.class).findById(entity.getIdBoletas())).withSelfRel(),
                linkTo(methodOn(BoletasControllerV2.class).findAll()).withRel("boletas")
                // Link.of("http://localhost:8005/api/v1/producto/"+entity.getIdBoletas()).withRel("producto")
        );
    }
}
