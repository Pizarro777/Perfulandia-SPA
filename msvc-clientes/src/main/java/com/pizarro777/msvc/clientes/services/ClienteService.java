package com.pizarro777.msvc.clientes.services;

import com.pizarro777.msvc.clientes.dtos.ClienteDTO;
import org.springframework.web.client.RestTemplate;

public class ClienteService {

    private final RestTemplate restTemplate;

    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO getClientePorId(Long id){
        return restTemplate.getForObject("https://localhost:8080/api/clientes/" + id, ClienteDTO.class);
    }
}
