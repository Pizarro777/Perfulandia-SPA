package com.pizarro777.msvc.carrito.assemblers;

import com.pizarro777.msvc.carrito.controller.CarritoController;
import com.pizarro777.msvc.carrito.dtos.ProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarritoProductoDTOModelAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>> {

    @Override
    public EntityModel<ProductoDTO> toModel(ProductoDTO entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(CarritoController.class).findById(entity.getId())).withRel("producto-carrito")
        );
    }
}

