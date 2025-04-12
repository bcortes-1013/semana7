package com.ejemplo.microservicio_peliculas.controller;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.service.PeliculaService;
import com.ejemplo.microservicio_peliculas.model.ResponseWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerPeliculas() {
        List<Pelicula> peliculas = peliculaService.obtenerPeliculas();

        if (peliculas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay películas registradas actualmente");
        }

        return ResponseEntity.ok(new ResponseWrapper<>("OK", peliculas.size(), peliculas));
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPeliculaPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Pelicula>> crearPelicula(@Valid @RequestBody Pelicula pelicula) {
        Pelicula creada = peliculaService.guardar(pelicula);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>("Película creada con éxito", 1, List.of(creada)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Pelicula>> actualizarPelicula(@PathVariable Long id, @Valid @RequestBody Pelicula peliculaActualizada) 
        {Pelicula actualizada = peliculaService.actualizar(id, peliculaActualizada);

        return ResponseEntity.ok(new ResponseWrapper<>("Película actualizada con éxito", 1, List.of(actualizada)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarPelicula(@PathVariable Long id) {
        peliculaService.eliminar(id);

        return ResponseEntity.ok(new ResponseWrapper<>("Película eliminada con éxito", 0, null));
    }
}
