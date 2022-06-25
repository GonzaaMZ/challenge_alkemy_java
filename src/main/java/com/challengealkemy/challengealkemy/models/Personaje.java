package com.challengealkemy.challengealkemy.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "personajes")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "edad")
    private int edad;

    @Column(name = "peso")
    private long peso;

    @Column(name = "historia", nullable = false)
    private String historia;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pelicula_personaje", joinColumns = @JoinColumn(name = "id_personaje", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id"))
    private Set<Pelicula> peliculas = new HashSet<>();

    public void addPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        pelicula.getPersonajes().add(this);
    }

    public void removePelicula(Pelicula pelicula) {
        peliculas.remove(pelicula);
        pelicula.getPersonajes().remove(this);
    }   

}
