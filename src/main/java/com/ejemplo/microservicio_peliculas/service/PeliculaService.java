package com.ejemplo.microservicio_peliculas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejemplo.microservicio_peliculas.exception.PeliculaNotFoundException;
import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.repository.PeliculaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PeliculaService {

    @Autowired
    private final PeliculaRepository repository;

    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    public List<Pelicula> obtenerPeliculas(){
        log.debug("Servicio: obtenerPeliculas()");

        return repository.findAll(Sort.by("id").ascending());
    } 

    public Pelicula obtenerPeliculaPorId(Long id){
        log.debug("Servicio: obtenerPeliculaPorId({})", id);

        return repository.findById(id).orElseThrow(() -> new PeliculaNotFoundException("No se ha encontrado el ID " + id));
    }

    public Pelicula guardar(Pelicula pelicula){
        log.debug("Servicio: guardar({})", pelicula.getTitulo());

        if(repository.existsById(pelicula.getId())){
            log.error("Ya existe una película con ID {}", pelicula.getId());

            throw new IllegalArgumentException("Ya existe una pelicula con el ID " + pelicula.getId());
        }

        return repository.save(pelicula);

    }

    public Pelicula actualizar(long id, Pelicula peliculaActualizada){
        log.debug("Servicio: actualizar({}, {})", id, peliculaActualizada.getTitulo());

        Pelicula existente = repository.findById(id).orElseThrow(() -> new PeliculaNotFoundException("No se ha encontrado la película"));

        existente.setTitulo(peliculaActualizada.getTitulo());
        existente.setAño(peliculaActualizada.getAño());
        existente.setDirector(peliculaActualizada.getDirector());
        existente.setGenero(peliculaActualizada.getGenero());
        existente.setSinopsis(peliculaActualizada.getSinopsis());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        log.debug("Servicio: eliminar({})", id);

        repository.findById(id).orElseThrow(() -> new PeliculaNotFoundException("Se ha eliminado la película ID " + id));

        repository.deleteById(id);
    }

    public List<Pelicula> buscarPorGenero(String genero) {
        return repository.findByGenero(genero);
    }
}
