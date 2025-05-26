package com.pizarro777.msvc.carrito.service;

import com.pizarro777.msvc.carrito.repositories.RepositoryCarrito;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService{

    private final RepositoryCarrito repositoryCarrito;

    public CarritoServiceImpl(RepositoryCarrito repositoryCarrito) {
        this.repositoryCarrito = repositoryCarrito;
    }

    //Mostra la lista de todos los carritos
    @Override
    public List<Carrito> findAll() {
        return repositoryCarrito.findAll();
    }

    //Busca un carrito por su ID
    @Override
    public Carrito findById(Long id) {
        return repositoryCarrito.findById(id).orElse(null);
    }

    //Guarda un carrito en la base de datos
    @Override
    public Carrito save(Carrito carrito) {
        return repositoryCarrito.save(carrito);
    }

    //Elimina un carrito por su ID
    @Override
    public void eliminarCarrito(Long id) {
        repositoryCarrito.deleteById(id);
    }
}
