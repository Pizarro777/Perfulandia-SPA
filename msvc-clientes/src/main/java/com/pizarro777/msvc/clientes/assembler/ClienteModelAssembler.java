package com.pizarro777.msvc.clientes.assembler;

import com.pizarro777.msvc.clientes.controllers.ClienteControllerV2;
import com.pizarro777.msvc.clientes.models.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClienteControllerV2.class).buscarClientePorId(entity.getId())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).obtenerTodos()).withRel("clientes")
        );
    }
}

