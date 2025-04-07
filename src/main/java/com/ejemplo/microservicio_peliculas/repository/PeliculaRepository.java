package com.ejemplo.microservicio_peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.microservicio_peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
}
