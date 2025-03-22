package com.ejemplo.microservicio_peliculas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class PeliculaNoEncontradaException extends RuntimeException {
    public PeliculaNoEncontradaException(Long id) {
        super("No se encontró la película con ID: " + id);
    }
}