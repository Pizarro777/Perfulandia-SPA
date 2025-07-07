package com.pizarro777.msvc.boletas.assemblers;

import com.pizarro777.msvc.boletas.controllers.BoletasControllerV2;
import com.pizarro777.msvc.boletas.models.entities.Boletas;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BoletasModelAssembler implements RepresentationModelAssembler<Boletas, EntityModel<Boletas>>  {

    @Override
    public EntityModel<Boletas> toModel(Boletas entity) {
        Link link = Link.of("http://localhost:8000/api/boletas/" + entity.getIdBoletas()).withRel("boletas");
        return EntityModel.of(
                entity,
                linkTo(methodOn(BoletasControllerV2.class).findBoletasProductoDTOById(entity.getIdBoletas())).withSelfRel(),
                linkTo(methodOn(BoletasControllerV2.class).findAllBoletasProductos()).withRel("boletas")
        );
    }
}
