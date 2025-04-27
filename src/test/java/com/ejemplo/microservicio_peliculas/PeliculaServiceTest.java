package com.ejemplo.microservicio_peliculas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Sort;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.repository.PeliculaRepository;
import com.ejemplo.microservicio_peliculas.service.PeliculaService;

public class PeliculaServiceTest {

    private PeliculaRepository peliculaRepository;
    private PeliculaService peliculaService;

    @BeforeEach
    public void setUp() {
        peliculaRepository = mock(PeliculaRepository.class);
        peliculaService = new PeliculaService(peliculaRepository);
    }

    @Test
    public void testObtenerTodas() {
        Pelicula p1 = new Pelicula(1L, "Pelicula 1", 2020, "Director 1", "Acción", "Sinopsis 1");
        Pelicula p2 = new Pelicula(2L, "Pelicula 2", 2021, "Director 2", "Comedia", "Sinopsis 2");

        when(peliculaRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> resultado = peliculaService.obtenerPeliculas();

        assertEquals(2, resultado.size());
        assertEquals("Pelicula 1", resultado.get(0).getTitulo());
    }

    @Test
    public void testObtenerPorId_existente() {
        Pelicula pelicula = new Pelicula(1L, "Pelicula Test", 2022, "Director X", "Drama", "Sinopsis");

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Pelicula resultado = peliculaService.obtenerPeliculaPorId(1L);

        assertEquals("Pelicula Test", resultado.getTitulo());
        assertEquals(2022, resultado.getAño());
    }

    @Test
    public void testGuardarPelicula() {
        Pelicula pelicula = new Pelicula(1L, "Inception", 2010, "Nolan", "Sci-Fi", "Sueños dentro de sueños");

        when(peliculaRepository.existsById(1L)).thenReturn(false);

        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula resultado = peliculaService.guardar(pelicula);

        assertNotNull(resultado);
        assertEquals("Inception", resultado.getTitulo());
        verify(peliculaRepository, times(1)).save(pelicula);
    }

    @Test
    public void testGuardarPeliculaConIdExistente() {
        Pelicula pelicula = new Pelicula(1L, "Inception", 2010, "Nolan", "Sci-Fi", "Sueños dentro de sueños");

        when(peliculaRepository.existsById(1L)).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            peliculaService.guardar(pelicula);
        });

        assertEquals("Ya existe una pelicula con el ID 1", thrown.getMessage());
    }

    @Test
    public void testActualizarPelicula() {
        Pelicula original = new Pelicula(1L, "Original", 2000, "Director Original", "Drama", "Sinopsis original");
        Pelicula actualizada = new Pelicula(1L, "Actualizada", 2022, "Nuevo Director", "Acción", "Nueva sinopsis");

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(original));
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(actualizada);

        Pelicula resultado = peliculaService.actualizar(1L, actualizada);

        assertEquals("Actualizada", resultado.getTitulo());
        assertEquals(2022, resultado.getAño());
        assertEquals("Nuevo Director", resultado.getDirector());
    }

    @Test
    public void testBuscarPorGenero() {
        Pelicula p1 = new Pelicula(1L, "Mad Max", 2015, "George Miller", "Acción", "Mundo post-apocalíptico");
        Pelicula p2 = new Pelicula(2L, "John Wick", 2014, "Chad Stahelski", "Acción", "Venganza de un asesino retirado");

        when(peliculaRepository.findByGenero("Acción")).thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> resultado = peliculaService.buscarPorGenero("Acción");

        assertEquals(2, resultado.size());
        assertEquals("Mad Max", resultado.get(0).getTitulo());
        assertEquals("John Wick", resultado.get(1).getTitulo());
    }

    @Test
    public void testEliminarPelicula() {
        Pelicula pelicula = new Pelicula(1L, "Inception", 2010, "Nolan", "Sci-Fi", "Sueños dentro de sueños");
    
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
    
        peliculaService.eliminar(1L);
    
        verify(peliculaRepository, times(1)).deleteById(1L);
    }
}
