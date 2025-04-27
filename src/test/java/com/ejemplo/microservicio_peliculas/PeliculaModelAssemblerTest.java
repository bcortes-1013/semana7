package com.ejemplo.microservicio_peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import com.ejemplo.microservicio_peliculas.hateoas.PeliculaModelAssembler;
import com.ejemplo.microservicio_peliculas.model.Pelicula;

public class PeliculaModelAssemblerTest {

    private PeliculaModelAssembler peliculaModelAssembler;

    @BeforeEach
    public void setUp() {
        peliculaModelAssembler = new PeliculaModelAssembler();
    }

    @Test
    public void testToModel() {
        // Crear un objeto de prueba
        Pelicula pelicula = new Pelicula(1L, "Inception", 2010, "Christopher Nolan", "Sci-Fi", "Dream within a dream");

        // Llamar al método toModel del assembler
        EntityModel<Pelicula> entityModel = peliculaModelAssembler.toModel(pelicula);

        // Verificar que el objeto tiene enlaces HATEOAS
        assertTrue(entityModel.hasLink("self"));
        assertTrue(entityModel.hasLink("delete"));
        assertTrue(entityModel.hasLink("update"));
        assertTrue(entityModel.hasLink("all"));

        // Verificar que los enlaces contienen las rutas correctas
        String selfLink = entityModel.getLink("self").get().getHref();
        assertTrue(selfLink.contains("/peliculas/1"));

        String deleteLink = entityModel.getLink("delete").get().getHref();
        assertTrue(deleteLink.contains("/peliculas/1"));

        String updateLink = entityModel.getLink("update").get().getHref();
        assertTrue(updateLink.contains("/peliculas/1"));

        String allLink = entityModel.getLink("all").get().getHref();
        assertTrue(allLink.contains("/peliculas"));
    }
}
