package com.pizarro777.msvc.inventarios.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ProductoServiceClient {

    private final RestTemplate restTemplate;

    public ProductoServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeProducto(Long productoId) {
        String url = "http://localhost:8080/api/productos/" + productoId;
        try {
            restTemplate.getForEntity(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
