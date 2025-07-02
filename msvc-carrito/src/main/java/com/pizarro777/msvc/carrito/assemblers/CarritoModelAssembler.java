package com.pizarro777.msvc.carrito.assemblers;

import com.pizarro777.msvc.carrito.controller.CarritoController;
import com.pizarro777.msvc.carrito.model.Carrito;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito entity) {
        Link link = Link.of("http://localhost:8005/api/carrito/" + entity.getIdCarrito()).withRel("carrito");
        return EntityModel.of(
                entity,
                linkTo(methodOn(CarritoController.class).findById(entity.getIdCarrito())).withSelfRel(),
                linkTo(methodOn(CarritoController.class).findAll()).withRel("carritos")
        );
    }
}
