package com.challengealkemy.challengealkemy.service;

import java.util.*;
import java.util.stream.Collectors;

import com.challengealkemy.challengealkemy.dto.PersonajeDTO;
import com.challengealkemy.challengealkemy.dto.PersonajeResponse;
import com.challengealkemy.challengealkemy.exceptions.ResourceNotFoundException;
import com.challengealkemy.challengealkemy.models.Pelicula;
import com.challengealkemy.challengealkemy.models.Personaje;
import com.challengealkemy.challengealkemy.repository.PeliculaRepository;
import com.challengealkemy.challengealkemy.repository.PersonajeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO) {
        Personaje personaje = mapearEntidad(personajeDTO);

        long idPelicula = personajeDTO.getIdPelicula();

        if (idPelicula == 0) {
            Personaje nuevoPersonaje = personajeRepository.save(personaje);

            PersonajeDTO personajeResponse = mapearDTO(nuevoPersonaje);

            return personajeResponse;
        }

        Set<Personaje> personajes = new HashSet<>();
        Set<Pelicula> peliculas = new HashSet<>();

        Pelicula pelicula = peliculaRepository.findById(idPelicula)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula", "ID", idPelicula));

        peliculas.add(pelicula);
        personajes.add(personaje);

        pelicula.setPersonajes(personajes);
        personaje.setPeliculas(peliculas);

        Personaje nuevoPersonaje = personajeRepository.save(personaje);

        PersonajeDTO personajeResponse = mapearDTO(nuevoPersonaje);

        return personajeResponse;
    }

    @Override
    public PersonajeDTO obtenerPersonajeById(long id) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", id));
        return mapearDTO(personaje);
    }

    @Override
    public PersonajeResponse obtenerPersonajes(String nombre, int edad, long idMovie) {

        if (nombre.equals("") && edad == -1 && idMovie == -1) {
            List<Personaje> personajes = personajeRepository.findAll();
            List<PersonajeDTO> listado = personajes.stream().map(personaje -> mapearDTO(personaje))
                    .collect(Collectors.toList());

            PersonajeResponse personajeResponse = new PersonajeResponse();
            personajeResponse.setListaDePersonajes(listado);
            return personajeResponse;
        }

        List<Personaje> personajes = personajeRepository.findAllByNombreOrEdad(nombre, edad);

        List<Personaje> personajesSegunPelicula = personajeRepository.findAllByPeliculas_id(idMovie);

        personajes.addAll(personajesSegunPelicula);

        List<PersonajeDTO> listado = personajes.stream().map(personaje -> mapearDTO(personaje))
                .collect(Collectors.toList());

        PersonajeResponse personajeResponse = new PersonajeResponse();
        personajeResponse.setListaDePersonajes(listado);

        return personajeResponse;

    }

    @Override
    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO, long id) {

        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "ID", id));

        personaje.setNombre(personajeDTO.getNombre());
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setImagen(personajeDTO.getImagen());
        personaje.setPeso(personajeDTO.getPeso());

        Personaje personajeActualizado = personajeRepository.save(personaje);
        return mapearDTO(personajeActualizado);
    }

    @Override
    public PersonajeDTO eliminarPersonaje(long id) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "ID", id));

        for (Pelicula pelicula : personaje.getPeliculas()) {
            personaje.removePelicula(pelicula);
        }

        personajeRepository.delete(personaje);

        return mapearDTO(personaje);
    }

    private PersonajeDTO mapearDTO(Personaje personaje) {
        PersonajeDTO personajeDTO = modelMapper.map(personaje, PersonajeDTO.class);

        return personajeDTO;
    }

    private Personaje mapearEntidad(PersonajeDTO personajeDTO) {
        Personaje personaje = modelMapper.map(personajeDTO, Personaje.class);
        return personaje;
    }

}
