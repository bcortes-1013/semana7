package com.ejemplo.microservicio_peliculas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejemplo.microservicio_peliculas.exception.GlobalExceptionHandler;
import com.ejemplo.microservicio_peliculas.exception.PeliculaNotFoundException;

public class ExceptionTest {

    @Test
    public void testHandlePeliculaNotFound() {
        PeliculaNotFoundException ex = new PeliculaNotFoundException("Pelicula no encontrada");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        ResponseEntity<Object> response = handler.handlePeliculaNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Pelicula no encontrada"));
    }

    @Test
    public void testHandleIllegalArgument() {
        IllegalArgumentException ex = new IllegalArgumentException("Argumento inválido");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        ResponseEntity<Object> response = handler.handleIllegalArgument(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Argumento inválido"));
    }

    @Test
    public void testHandleGeneric() {
        Exception ex = new Exception("Error interno");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        ResponseEntity<Object> response = handler.handleGeneric(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Error interno"));
    }

    @Test
    public void testGlobalExceptionHandler() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        assertNotNull(handler);
    }
}
