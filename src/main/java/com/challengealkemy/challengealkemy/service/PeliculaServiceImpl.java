package com.challengealkemy.challengealkemy.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.challengealkemy.challengealkemy.dto.PeliculaDTO;
import com.challengealkemy.challengealkemy.exceptions.ResourceNotFoundException;
import com.challengealkemy.challengealkemy.models.Genero;
import com.challengealkemy.challengealkemy.models.Pelicula;
import com.challengealkemy.challengealkemy.models.Personaje;
import com.challengealkemy.challengealkemy.repository.GeneroRepository;
import com.challengealkemy.challengealkemy.repository.PeliculaRepository;
import com.challengealkemy.challengealkemy.repository.PersonajeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonajeRepository personajeRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Override
    public PeliculaDTO crearPelicula(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = mapearAEntidad(peliculaDTO);

        long idPersonaje = peliculaDTO.getIdPersonaje();
        long idGenero = peliculaDTO.getIdGenero();

        Set<Personaje> personajes = new HashSet<>();
        Set<Pelicula> peliculas = new HashSet<>();

        Genero genero = generoRepository.findById(idGenero)
        .orElseThrow(() -> new ResourceNotFoundException("Genero", "ID", idGenero));
        
        pelicula.setGenero(genero);
        
        if (idPersonaje == 0) {
            Pelicula peliculaNueva = peliculaRepository.save(pelicula);

            PeliculaDTO peliculaRes = mapearADTO(peliculaNueva);

            return peliculaRes;
        }


        Personaje personaje = personajeRepository.findById(idPersonaje)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "ID", idPersonaje));

        peliculas.add(pelicula);
        personajes.add(personaje);

        pelicula.setPersonajes(personajes);
        personaje.setPeliculas(peliculas);
        genero.setPeliculasAsociadas(peliculas);

        Pelicula peliculaNueva = peliculaRepository.save(pelicula);

        PeliculaDTO peliculaRes = mapearADTO(peliculaNueva);

        return peliculaRes;
    }

    @Override
    public PeliculaDTO obtenerPeliculaById(long idPelicula) {
        Pelicula pelicula = peliculaRepository.findById(idPelicula)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula", "ID", idPelicula));

        return mapearADTO(pelicula);

    }

    @Override
    public List<PeliculaDTO> obtenerPeliculas(String titulo, long idGenero, String sortDir) {

        if(titulo.equals("") && idGenero == -1){
            List<Pelicula> peliculas = peliculaRepository.findAll();
            List<PeliculaDTO> listadoPeliculas = peliculas.stream().map(pelicula -> mapearADTO(pelicula))
                .collect(Collectors.toList());

            return listadoPeliculas;
        }

        
        List<Pelicula> peliculas = peliculaRepository.findByTitulo(titulo);
        
        List<Pelicula> peliculasByGenero = peliculaRepository.findAllByGenero_id(idGenero);
        
        peliculas.addAll(peliculasByGenero);
        
       
        
        List<PeliculaDTO> listadoPeliculas = peliculas.stream().map(pelicula -> mapearADTO(pelicula))
        .collect(Collectors.toList());
        
        return listadoPeliculas;
    }

    @Override
    public PeliculaDTO actualizarPelicula(PeliculaDTO peliculaDTO, long idPelicula) {
        Pelicula pelicula = peliculaRepository.findById(idPelicula)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula", "ID", idPelicula));

        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setImagen(peliculaDTO.getImagen());
        pelicula.setCalificacion(peliculaDTO.getCalificacion());
        pelicula.setFechaCreacion(peliculaDTO.getFechaCreacion());

        Pelicula peliculaActualizada = peliculaRepository.save(pelicula);
        return mapearADTO(peliculaActualizada);
    }

    @Override
    public PeliculaDTO eliminarPelicula(long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula", "ID", id));

        for (Personaje personaje : pelicula.getPersonajes()) {
            pelicula.removePersonaje(personaje);
        }

        peliculaRepository.delete(pelicula);
        return mapearADTO(pelicula);
    }

    @Override
    public PeliculaDTO añadirPersonaje(long idPersonaje, long idPelicula) {

        Personaje personaje = personajeRepository.findById(idPersonaje).orElseThrow(() -> new ResourceNotFoundException("Personaje", "ID", idPersonaje));
        Pelicula pelicula = peliculaRepository.findById(idPelicula).orElseThrow(() -> new ResourceNotFoundException("Pelicula", "ID", idPelicula));
        
       pelicula.addPersonaje(personaje);

       peliculaRepository.save(pelicula);

       PeliculaDTO peliculaResponse = mapearADTO(pelicula);
    
       return peliculaResponse;
    }

    @Override
    public PeliculaDTO removerPersonaje(long idPersonaje, long idPelicula) {
        Personaje personaje = personajeRepository.findById(idPersonaje).orElseThrow(() -> new ResourceNotFoundException("Personaje", "ID", idPersonaje));
        Pelicula pelicula = peliculaRepository.findById(idPelicula).orElseThrow(() -> new ResourceNotFoundException("Pelicula", "ID", idPelicula));
        
        pelicula.removePersonaje(personaje);

        peliculaRepository.save(pelicula);

        PeliculaDTO peliculaResponse = mapearADTO(pelicula);

        return peliculaResponse;
    }
    private PeliculaDTO mapearADTO(Pelicula pelicula) {
        PeliculaDTO peliculaDTO = modelMapper.map(pelicula, PeliculaDTO.class);
        return peliculaDTO;
    }

    private Pelicula mapearAEntidad(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = modelMapper.map(peliculaDTO, Pelicula.class);
        return pelicula;
    }



}
