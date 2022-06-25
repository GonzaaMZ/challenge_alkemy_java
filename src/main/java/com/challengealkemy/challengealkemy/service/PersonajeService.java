package com.challengealkemy.challengealkemy.service;


import com.challengealkemy.challengealkemy.dto.PersonajeDTO;
import com.challengealkemy.challengealkemy.dto.PersonajeResponse;


public interface PersonajeService {
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO);

    public PersonajeResponse obtenerPersonajes(String nombre , int edad, long idMovie);

    public PersonajeDTO obtenerPersonajeById(long id);

    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO, long id);

    public PersonajeDTO eliminarPersonaje(long id);

}
