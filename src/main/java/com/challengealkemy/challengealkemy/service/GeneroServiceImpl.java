package com.challengealkemy.challengealkemy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challengealkemy.challengealkemy.dto.GeneroDTO;
import com.challengealkemy.challengealkemy.models.Genero;
import com.challengealkemy.challengealkemy.repository.GeneroRepository;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GeneroDTO crearGenero(GeneroDTO generoDTO) {
        Genero genero = mapearAEntidad(generoDTO);

        Genero generoNuevo = generoRepository.save(genero);

        GeneroDTO generoRes = mapearADto(generoNuevo);

        return generoRes;
    }

    private GeneroDTO mapearADto(Genero genero){
        GeneroDTO generoDTO = modelMapper.map(genero, GeneroDTO.class);
        return generoDTO;
    }

    private Genero mapearAEntidad(GeneroDTO generoDTO){
        Genero genero = modelMapper.map(generoDTO, Genero.class);
        return genero;
    }
    
}
