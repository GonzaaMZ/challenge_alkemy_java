package com.challengealkemy.challengealkemy.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.challengealkemy.challengealkemy.models.Pelicula;

import lombok.*;

@Data
public class GeneroDTO {
    
    private Long id;

    @NotEmpty(message = "Debe tener un nombre")
    private String nombre;

    private String imagen;

    private Set<Pelicula> peliculasAsociadas = new HashSet<>();

}
