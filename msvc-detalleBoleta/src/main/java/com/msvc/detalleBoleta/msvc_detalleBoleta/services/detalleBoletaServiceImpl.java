package com.msvc.detalleBoleta.msvc_detalleBoleta.services;


import com.msvc.detalleBoleta.msvc_detalleBoleta.clients.ProductoClientRest;
import com.msvc.detalleBoleta.msvc_detalleBoleta.repositories.detalleBoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class detalleBoletaServiceImpl {

    @Autowired
    private detalleBoletaRepository detalleBoletaRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

}
