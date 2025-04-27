package com.ejemplo.microservicio_peliculas;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.repository.PeliculaRepository;

@DataJpaTest
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void testGuardarYBuscar() {
        Pelicula pelicula = new Pelicula(5L, "Titanic", 1997, "Cameron", "Romance", "Historia del barco");

        peliculaRepository.save(pelicula);

        Optional<Pelicula> encontrada = peliculaRepository.findById(5L);

        assertTrue(encontrada.isPresent());

        assertEquals("Titanic", encontrada.get().getTitulo());
    }
}
