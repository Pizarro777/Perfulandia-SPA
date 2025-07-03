package com.pizarro777.msvc.boletas.assemblers;

import com.pizarro777.msvc.boletas.controllers.BoletasControllerV2;
import com.pizarro777.msvc.boletas.dtos.BoletasProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BoletasProductoDTOModelAssembler implements RepresentationModelAssembler<BoletasProductoDTO, EntityModel<BoletasProductoDTO>> {

    @Override
    public EntityModel<BoletasProductoDTO> toModel(BoletasProductoDTO boletasProductoDTO) {
        return EntityModel.of(
                boletasProductoDTO,
                linkTo(methodOn(BoletasControllerV2.class).findBoletasProductoDTOById(boletasProductoDTO.getIdProducto())).withSelfRel(),
                linkTo(methodOn(BoletasControllerV2.class).findAllBoletasProductos()).withRel("productos")
        );
    }
}