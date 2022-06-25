package com.challengealkemy.challengealkemy.repository;

import java.util.List;

import com.challengealkemy.challengealkemy.models.Personaje;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    public List<Personaje> findAllByNombreOrEdad(String nombre, int edad);

    public List<Personaje> findAllByPeliculas_id(long idMovie);

}
