package com.ejemplo.microservicio_peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.ejemplo.microservicio_peliculas.model.Pelicula;

public class PeliculaModelTest {

    @Test
    public void testEsReciente() {
        Pelicula pelicula = new Pelicula(1L, "Nueva Era", 2023, "Director X", "Sci-Fi", "Descripción");
        assertTrue(pelicula.esReciente());
    }
}