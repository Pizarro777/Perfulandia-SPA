package com.pizarro777.msvc.carrito.service;

import com.pizarro777.msvc.carrito.clients.ProductoClientRest;
import com.pizarro777.msvc.carrito.dtos.ProductoDTO;
import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.model.ItemCarrito;
import com.pizarro777.msvc.carrito.repositories.RepositoryCarrito;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService{

    @Autowired
    private RepositoryCarrito repositoryCarrito;

    @Autowired
    private ProductoClientRest productoClientRest;

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

    //Crear un carrito en la base de datos
    @Override
    public Carrito save(Carrito carrito) {
        for (ItemCarrito item : carrito.getItems()) {
            ProductoDTO producto = productoClientRest.obtenerProducto(item.getIdProducto());

            item.setNombre( producto.getNombre());
            item.setMarca( producto.getMarca());
            item.setPrecio(producto.getPrecio());
            item.setCarrito(carrito);

        }

        return repositoryCarrito.save(carrito);
    }

    //Elimina un carrito por su ID
    @Override
    public void eliminarCarrito(Long id) {
        repositoryCarrito.deleteById(id);
    }

    //Calcula el precio total de un carrito
    @Override
    public Double precioTotal(Long idCarrito) {
        return repositoryCarrito.findById(idCarrito)
                .map(Carrito::calcularPrecioTotal)
                .orElse(0.0);
    }
}
