package com.ejemplo.microservicio_peliculas.service;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.exception.PeliculaNotFoundException;
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
        return repository.findById(id).orElseThrow(() -> new PeliculaNotFoundException("No se ha encontrado el ID " + id));
    }

    public Pelicula guardar(Pelicula pelicula){
        if(repository.existsById(pelicula.getId())){
            throw new IllegalArgumentException("Ya existe una pelicula con el ID " + pelicula.getId());
        }

        return repository.save(pelicula);

    }

    public Pelicula actualizar(long id, Pelicula peliculaActualizada){
        Pelicula existente = repository.findById(id).orElseThrow(() -> new PeliculaNotFoundException("No se ha encontrado la película"));

        existente.setTitulo(peliculaActualizada.getTitulo());
        existente.setAño(peliculaActualizada.getAño());
        existente.setDirector(peliculaActualizada.getDirector());
        existente.setGenero(peliculaActualizada.getGenero());
        existente.setSinopsis(peliculaActualizada.getSinopsis());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Pelicula existente = repository.findById(id).orElseThrow(() -> new PeliculaNotFoundException("Se ha eliminado la película ID " + id));

        repository.delete(existente);
    }
}
