package com.pizarro777.msvc.clientes.controllers;

import com.pizarro777.msvc.clientes.dtos.ClienteDTO;
import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.services.ClienteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerTodos(){
        return ClienteService.listarTodos();
    }


}
