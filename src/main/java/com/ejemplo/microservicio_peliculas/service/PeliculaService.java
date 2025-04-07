package com.ejemplo.microservicio_peliculas.service;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.exception.NoEncontradoException;
import com.ejemplo.microservicio_peliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repository;

    public List<Pelicula> obtenerPeliculas(){
        return repository.findAll();
    } 

    public Pelicula obtenerPeliculaPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NoEncontradoException("No se ha encontrado el ID " + id));
    }
}
