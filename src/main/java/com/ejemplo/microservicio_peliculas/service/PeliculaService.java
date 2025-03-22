package com.ejemplo.microservicio_peliculas.service;

import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.exception.PeliculaNoEncontradaException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

@Service
public class PeliculaService {
    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaService(){
        peliculas.add(new Pelicula(1L, "El Padrino", 1972, "Francis Ford Coppola", "Crimen, Drama", "La historia de la familia Corleone y su lucha por el poder en el mundo del crimen organizado."));
        peliculas.add(new Pelicula(2L, "Titanic", 1997, "James Cameron", "Romance, Drama", "Un amor imposible surge entre dos pasajeros de distintas clases sociales a bordo del Titanic."));
        peliculas.add(new Pelicula(3L, "Inception", 2010, "Christopher Nolan", "Ciencia ficción, Acción", "Un ladrón que entra en los sueños de las personas para robar secretos intenta implantar una idea en la mente de alguien."));
        peliculas.add(new Pelicula(4L, "Interstellar", 2014, "Christopher Nolan", "Ciencia ficción, Drama", "Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad."));
        peliculas.add(new Pelicula(5L, "Pulp Fiction", 1994, "Quentin Tarantino", "Crimen, Drama", "Historias entrelazadas de crimen, venganza y redención en Los Ángeles."));
        peliculas.add(new Pelicula(6L, "El Señor de los Anillos: El Retorno del Rey", 2003, "Peter Jackson", "Fantasía, Aventura", "Frodo y Sam se acercan al Monte del Destino mientras la guerra final entre el bien y el mal se desata en la Tierra Media."));
        peliculas.add(new Pelicula(7L, "Forrest Gump", 1994, "Robert Zemeckis", "Drama, Romance", "La vida de un hombre con un coeficiente intelectual bajo que, sin darse cuenta, participa en varios momentos históricos clave de EE.UU."));
        peliculas.add(new Pelicula(8L, "Matrix", 1999, "Lana Wachowski, Lilly Wachowski", "Ciencia ficción, Acción", "Un hacker descubre que el mundo en el que vive es una simulación creada por máquinas y se une a una rebelión contra ellas."));
        peliculas.add(new Pelicula(9L, "El Rey León", 1994, "Roger Allers, Rob Minkoff", "Animación, Aventura", "Un joven león exiliado regresa a su hogar para reclamar su lugar como rey tras la trágica muerte de su padre."));
        peliculas.add(new Pelicula(10L, "Joker", 2019, "Todd Phillips", "Crimen, Drama", "Un comediante con problemas mentales es rechazado por la sociedad y se convierte en el villano más temido de Gotham."));
    }

    public List<Pelicula> obtenerPeliculas(){
        return peliculas;
    }

    public Pelicula obtenerPeliculaPorId(Long id){
        return peliculas.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }
}
