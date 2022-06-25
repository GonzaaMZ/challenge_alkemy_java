package com.challengealkemy.challengealkemy.models;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @JsonBackReference
    @OneToMany(mappedBy = "genero")
    private Set<Pelicula> peliculasAsociadas = new HashSet<>();
}
