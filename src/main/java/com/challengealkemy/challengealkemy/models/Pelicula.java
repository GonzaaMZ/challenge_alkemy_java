package com.challengealkemy.challengealkemy.models;

import java.util.*;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "peliculas")
public class Pelicula{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "imagen")
    private String imagen;
    
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @JsonBackReference
    @ManyToMany(mappedBy = "peliculas", cascade = CascadeType.PERSIST)
    private Set<Personaje> personajes = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genero_id")
    private Genero genero;
   
    public void addPersonaje(Personaje personaje){
        personajes.add(personaje);
        personaje.getPeliculas().add(this);
    }

    public void removePersonaje(Personaje personaje){
        personajes.remove(personaje);
        personaje.getPeliculas().remove(this);
    }

}


