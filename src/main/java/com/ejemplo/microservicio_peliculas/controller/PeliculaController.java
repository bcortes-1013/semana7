package com.ejemplo.microservicio_peliculas.controller;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.service.PeliculaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Optional;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerPeliculas() {
        return peliculaService.obtenerPeliculas();
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPeliculaPorId(@PathVariable("id") Long id) {
        return peliculaService.obtenerPeliculaPorId(id);
    }
    
}
