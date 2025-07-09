package com.pizarro777.msvc.productos.assemblers;

import com.pizarro777.msvc.productos.controllers.ProductoController;
import com.pizarro777.msvc.productos.models.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductoController.class).obtenerProducto(entity.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).obtenerTodos()).withRel("productos")
        );
    }
}
