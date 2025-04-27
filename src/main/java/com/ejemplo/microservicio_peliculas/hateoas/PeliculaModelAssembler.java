package com.ejemplo.microservicio_peliculas.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ejemplo.microservicio_peliculas.controller.PeliculaController;
import com.ejemplo.microservicio_peliculas.model.Pelicula;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Esta clase convierte una entidad Pelicula en un EntityModel<Pelicula>
 * enriquecido con enlaces HATEOAS.
 * 
 * Implementa RepresentationModelAssembler, que es una interfaz de Spring
 * HATEOAS
 * para transformar objetos de dominio (Pelicula) en recursos RESTful
 * enriquecidos.
 */
@Component
public class PeliculaModelAssembler implements RepresentationModelAssembler<Pelicula, EntityModel<Pelicula>> {

    @Override
    public EntityModel<Pelicula> toModel(Pelicula pelicula) {
        return EntityModel.of(
                pelicula, // Entidad original

                // Enlace al detalle de la película (GET /peliculas/{id})
                linkTo(methodOn(PeliculaController.class)
                        .obtenerPeliculaPorId(pelicula.getId()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /peliculas/{id})
                linkTo(methodOn(PeliculaController.class)
                        .eliminarPelicula(pelicula.getId()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /peliculas/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(PeliculaController.class)
                        .actualizarPelicula(pelicula.getId(), null))
                        .withRel("update"),

                // Enlace para ver todas las películas (GET /peliculas)
                linkTo(methodOn(PeliculaController.class)
                        .obtenerPeliculas())
                        .withRel("all"));
    }
}
