package com.ejemplo.microservicio_peliculas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.microservicio_peliculas.hateoas.PeliculaModelAssembler;
import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.model.ResponseWrapper;
import com.ejemplo.microservicio_peliculas.service.PeliculaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;
    private final PeliculaModelAssembler assembler;


    public PeliculaController(PeliculaService peliculaService, PeliculaModelAssembler assembler){
        this.peliculaService = peliculaService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pelicula>>> obtenerPeliculas() {
        log.info("GET /peliculas - Obteniendo todas las películas");

        List<Pelicula> peliculas = peliculaService.obtenerPeliculas();

        if (peliculas.isEmpty()) {
            log.warn("No hay películas registradas actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Pelicula>> modelos = peliculas.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(PeliculaController.class).obtenerPeliculas()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> obtenerPeliculaPorId(@PathVariable Long id) {
        log.info("GET /peliculas/{} - Buscando película por ID", id);

        Pelicula pelicula = peliculaService.obtenerPeliculaPorId(id);

        return ResponseEntity.ok(assembler.toModel(pelicula));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pelicula>> crearPelicula(@Valid @RequestBody Pelicula pelicula) {
        log.info("POST /peliculas - Creando película: {}", pelicula.getTitulo());

        Pelicula creada = peliculaService.guardar(pelicula);

        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(creada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> actualizarPelicula(@PathVariable Long id, @Valid @RequestBody Pelicula peliculaActualizada) {
        log.info("PUT /peliculas/{} - Actualizando película", id);
        
        Pelicula actualizada = peliculaService.actualizar(id, peliculaActualizada);

        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarPelicula(@PathVariable Long id) {
        log.warn("DELETE /peliculas/{} - Eliminando película", id);

        peliculaService.eliminar(id);

        return ResponseEntity.ok(new ResponseWrapper<>("Película eliminada con éxito", 0, null));
    }
}
