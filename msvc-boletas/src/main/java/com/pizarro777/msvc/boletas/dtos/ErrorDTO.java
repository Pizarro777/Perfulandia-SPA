package com.pizarro777.msvc.boletas.dtos;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorDTO {

    private int status;
    private LocalDateTime date;
    private Map<String, String> errors;

    // Getters y Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }


}
