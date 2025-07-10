package com.pizarro777.msvc.boletas.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter

public class ErrorDTO {

    private int status;
    private LocalDateTime date;
    private Map<String, String> errors;


    @Override
    public String toString() {
        return "ErrorDTO{" +
                "status=" + status +
                ", date=" + date +
                ", errors=" + errors +
                '}';
    }
}
