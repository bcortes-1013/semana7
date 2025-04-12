package com.ejemplo.microservicio_peliculas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class PeliculaNotFoundException extends RuntimeException {
    public PeliculaNotFoundException(String mensaje) {
        super(mensaje);
    }
}