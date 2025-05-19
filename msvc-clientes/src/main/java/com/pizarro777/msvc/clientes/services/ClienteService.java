package com.pizarro777.msvc.clientes.services;


import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    /* Crear o Actualizar Cliente */
    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }


}
