package com.challengealkemy.challengealkemy.repository;

import com.challengealkemy.challengealkemy.models.Pelicula;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>  {

    public List<Pelicula> findByTitulo(String titulo);

    public List<Pelicula> findAllByGenero_id(long idGenero);
    
}
