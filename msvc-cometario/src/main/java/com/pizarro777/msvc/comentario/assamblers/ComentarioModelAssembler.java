package com.pizarro777.msvc.comentario.assamblers;


import com.pizarro777.msvc.comentario.controller.ComentarioController;
import com.pizarro777.msvc.comentario.model.Comentario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComentarioModelAssembler implements RepresentationModelAssembler<Comentario, EntityModel<Comentario>> {

    @Override
    public EntityModel<Comentario> toModel(Comentario entity) {
        Link link = Link.of("http://localhost:8003/api/comentarios/" + entity.getIdComentario()).withRel("comentario");
        return EntityModel.of(
                entity,
                linkTo(methodOn(ComentarioController.class).findById(entity.getIdComentario())).withSelfRel(),
                linkTo(methodOn(ComentarioController.class).findAll()).withRel("comentarios")
        );
    }

}
