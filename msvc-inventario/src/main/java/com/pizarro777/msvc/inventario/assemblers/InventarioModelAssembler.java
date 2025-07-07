package com.pizarro777.msvc.inventario.assemblers;

import com.pizarro777.msvc.inventario.controller.InventarioController;
import com.pizarro777.msvc.inventario.model.Inventario;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class InventarioModelAssembler {public EntityModel<Inventario> toModel(Inventario entity) {

    Link link = Link.of("http://localhost:8004/api/inventarios/" + entity.getId()).withRel("inventario");
    return EntityModel.of(
            entity,
            linkTo(methodOn(InventarioController.class).obtenerInventario(entity.getId())).withSelfRel(),
            linkTo(methodOn(InventarioController.class).listarInventarios()).withRel("inventario")
    );
}
}
