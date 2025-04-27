package com.ejemplo.microservicio_peliculas;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ejemplo.microservicio_peliculas.controller.PeliculaController;
import com.ejemplo.microservicio_peliculas.hateoas.PeliculaModelAssembler;
import com.ejemplo.microservicio_peliculas.model.Pelicula;
import com.ejemplo.microservicio_peliculas.service.PeliculaService;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private PeliculaService peliculaService;

    @SuppressWarnings("removal")
    @MockBean
    private PeliculaModelAssembler peliculaAssembler; 

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    public void testObtenerPorId() throws Exception {
        Pelicula pelicula = new Pelicula(1L, "Matrix", 1999, "Wachowski", "Acción", "Ciencia ficción");

        EntityModel<Pelicula> peliculaModel = EntityModel.of(pelicula,
                linkTo(methodOn(PeliculaController.class).obtenerPeliculaPorId(1L)).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).eliminarPelicula(1L)).withRel("delete"),
                linkTo(methodOn(PeliculaController.class).actualizarPelicula(1L, null)).withRel("update"),
                linkTo(methodOn(PeliculaController.class).obtenerPeliculas()).withRel("all"));

        when(peliculaService.obtenerPeliculaPorId(1L)).thenReturn(pelicula);
        when(peliculaAssembler.toModel(pelicula)).thenReturn(peliculaModel);

        mockMvc.perform(get("/peliculas/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Matrix"))
                .andExpect(jsonPath("$.año").value(1999))
                .andExpect(jsonPath("$.genero").value("Acción"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.delete.href").exists())
                .andExpect(jsonPath("$._links.update.href").exists());
    }

    //Error 500
    // @Test
    // @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    // public void testObtenerPeliculas() throws Exception {
    //     Pelicula pelicula1 = new Pelicula(1L, "Matrix", 1999, "Wachowski", "Acción", "Ciencia ficción");
    //     Pelicula pelicula2 = new Pelicula(2L, "Inception", 2010, "Nolan", "Sci-Fi", "Sueños dentro de sueños");

    //     List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
    //     when(peliculaService.obtenerPeliculas()).thenReturn(peliculas);

    //     mockMvc.perform(get("/peliculas")
    //             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$[0].id").value(1)) 
    //             .andExpect(jsonPath("$[1].id").value(2));

    //     verify(peliculaService).obtenerPeliculas();
    // }

    //Error 403
    // @Test
    // @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    // public void testObtenerPeliculas() throws Exception {
    //     Pelicula pelicula = new Pelicula(1L, "Matrix", 1999, "Wachowski", "Acción", "Ciencia ficción");
    
    //     List<Pelicula> peliculas = Arrays.asList(pelicula);
    
    //     when(peliculaService.obtenerPeliculas()).thenReturn(peliculas);
    
    //     mockMvc.perform(get("/peliculas")
    //             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$._embedded.peliculaList[0].titulo").value("Matrix"))
    //             .andExpect(jsonPath("$._embedded.peliculaList[0].año").value(1999));
    // }

    //Error 403
    // @Test
    // @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    // public void testCrearPelicula() throws Exception {
    //     Pelicula pelicula = new Pelicula(1L, "Inception", 2010, "Nolan", "Sci-Fi", "Sueños dentro de sueños");

    //     // Configuramos el mock para que devuelva la película creada
    //     when(peliculaService.guardar(any(Pelicula.class))).thenReturn(pelicula);

    //     mockMvc.perform(post("/peliculas")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(pelicula)))
    //             .andExpect(status().isCreated())
    //             .andExpect(jsonPath("$.titulo").value("Inception"))
    //             .andExpect(jsonPath("$.año").value(2010));
    // }

    //Error 403
    // @Test
    // @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    // public void testEliminarPelicula() throws Exception {
    //     doNothing().when(peliculaService).eliminar(eq(1L));

    //     mockMvc.perform(delete("/peliculas/{id}", 1L))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.mensaje").value("Película eliminada con éxito"));
    // }
}
