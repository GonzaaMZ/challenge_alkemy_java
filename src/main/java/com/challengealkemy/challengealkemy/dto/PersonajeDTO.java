package com.challengealkemy.challengealkemy.dto;

import java.util.Set;

import javax.validation.constraints.*;

import com.challengealkemy.challengealkemy.models.Pelicula;

import lombok.*;

public class PersonajeDTO {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String imagen;

    @Getter
    @Setter
    @NotEmpty(message = "El Nombre no debe estar vacio o nulo")
    private String nombre;

    @Getter
    @Setter
    private int edad;

    @Getter
    @Setter
    private String pelicula;

    @Getter
    @Setter
    @NotEmpty(message = "La Historia no debe estar vacio o nulo")
    @Size(min = 10, message = "La Historia del personaje debe tener al menos 10 caracteres")
    private String historia;

    @Getter
    @Setter
    private long peso;

    @Getter @Setter
    private long idPelicula;

    @Getter @Setter
    private Set<Pelicula> peliculas;

    public PersonajeDTO() {
    }

    public PersonajeDTO(Long id, String imagen,
            @NotEmpty(message = "El Nombre no debe estar vacio o nulo") String nombre, int edad, String pelicula,
            @NotEmpty(message = "La Historia no debe estar vacio o nulo") @Size(min = 10, message = "La Historia del personaje debe tener al menos 10 caracteres") String historia,
            long peso, Set<Pelicula> peliculas) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.pelicula = pelicula;
        this.historia = historia;
        this.peso = peso;
        this.peliculas = peliculas;
    }

    

}
