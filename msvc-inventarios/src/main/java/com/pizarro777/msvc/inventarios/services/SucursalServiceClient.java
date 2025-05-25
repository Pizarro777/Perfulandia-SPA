package com.pizarro777.msvc.inventarios.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class SucursalServiceClient {

    private final RestTemplate restTemplate;

    public SucursalServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeSucursal(Long sucursalId) {
        String url = "http://localhost:8083/api/sucursales/" + sucursalId;
        try {
            restTemplate.getForEntity(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
