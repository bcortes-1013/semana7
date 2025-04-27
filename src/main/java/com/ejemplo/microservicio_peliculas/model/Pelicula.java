package com.ejemplo.microservicio_peliculas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="peliculas")
public class Pelicula{
    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;
    
    @NotBlank(message = "Título no puede ser vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 carácteres")
    private String titulo;

    @Min(value = 1800, message = "El año debe ser mayor a 1800")
    @Max(value = 2025, message = "El año no puede ser mayor al actual")
    private int año;

    @NotBlank(message = "Director no puede ser vacío")
    @Size(min = 1, max = 100, message = "El director debe tener entre 1 y 100 carácteres")
    private String director;

    @NotBlank(message = "El género no puede estar vacío")
    @Size(min = 3, max = 50, message = "El género debe tener entre 3 y 50 caracteres")
    private String genero;

    @Size(max = 255, message = "La sinopsis no debe superar los 255 caracteres")
    private String sinopsis;

    public boolean esReciente() {
        return this.año >= 2020;
    }
}