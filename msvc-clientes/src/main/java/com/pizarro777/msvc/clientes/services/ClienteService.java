package com.pizarro777.msvc.clientes.services;


import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    /* Crear  Cliente */
    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    /* Listar Por Id */
    public Cliente obtenerPorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(()-> new RuntimeException("No se encontro el id en la base de datos: "+ id));
    }

    /* Listar todos */
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    /* Actualizar Cliente */
    public Cliente actualizarCliente(Long id, Cliente datos){
        Cliente cli = obtenerPorId(id);

        /* Actualiza todos los datos */
        cli.setRut(datos.getRut());
        cli.setNombre(datos.getNombre());
        cli.setApellido(datos.getApellido());
        cli.setRut(datos.getRut());
        cli.setCorreo(datos.getCorreo());
        cli.setDireccion(datos.getDireccion());

        return clienteRepository.save(cli);
    }

    /* Eliminar Cliente */
    public void eliminar(long id){
        clienteRepository.deleteById(id);
    }

}
