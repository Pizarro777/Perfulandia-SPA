package com.pizarro777.msvc.sucursales.assemblers;

import com.pizarro777.msvc.sucursales.controllers.SucursalController;
import com.pizarro777.msvc.sucursales.dtos.SucursalDTO;
import com.pizarro777.msvc.sucursales.models.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<SucursalDTO>> {

    @Override
    @NonNull
    public EntityModel<SucursalDTO> toModel(@NonNull Sucursal sucursal) {
        // Convertir entidad a DTO
        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());

        return EntityModel.of(dto,
                linkTo(methodOn(SucursalController.class).obtenerSucursal(sucursal.getId())).withSelfRel(),
                linkTo(methodOn(SucursalController.class).obtenerTodas()).withRel("sucursales"));
    }
}