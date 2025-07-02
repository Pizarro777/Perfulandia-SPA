package com.pizarro777.msvc.comentario.assemblers;

import com.pizarro777.msvc.comentario.controller.ComentarioController;
import com.pizarro777.msvc.comentario.dtos.ProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComentarioProductoDTOAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>> {

    @Override
    public EntityModel<ProductoDTO> toModel(ProductoDTO entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ComentarioController.class).findById(entity.getId())).withRel("producto-comentario")
        );
    }
}
