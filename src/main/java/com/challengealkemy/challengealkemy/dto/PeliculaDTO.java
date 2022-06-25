package com.challengealkemy.challengealkemy.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.challengealkemy.challengealkemy.models.Genero;
import com.challengealkemy.challengealkemy.models.Personaje;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PeliculaDTO {

    private Long id;

    @NotEmpty(message = "Debe tener un titulo")
    private String titulo;

    private String imagen;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaCreacion;

    private int calificacion;

    private long idPersonaje;

    private long idGenero;

    private Genero genero;
    
    private Set<Personaje> personajes;

}