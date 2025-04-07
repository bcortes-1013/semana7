package com.ejemplo.microservicio_peliculas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="peliculas")

public class Pelicula{
    @Id
    private Long id;
    private String titulo;
    private int año;
    private String director;
    private String genero;
    private String sinopsis;
}