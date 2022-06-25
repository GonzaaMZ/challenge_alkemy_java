package com.challengealkemy.challengealkemy.service;

import java.util.List;

import com.challengealkemy.challengealkemy.dto.PeliculaDTO;

public interface PeliculaService {
    
    public PeliculaDTO crearPelicula(PeliculaDTO peliculaDTO);

    public PeliculaDTO obtenerPeliculaById(long idPelicula);

    public List<PeliculaDTO> obtenerPeliculas(String titulo, long idGenero, String sortDir);

    public PeliculaDTO actualizarPelicula(PeliculaDTO peliculaDTO ,long idPelicula);

    public PeliculaDTO eliminarPelicula(long id);

    public PeliculaDTO añadirPersonaje(long idPersonaje, long idPelicula);

    public PeliculaDTO removerPersonaje(long idPersonaje, long idPelicula);

}
