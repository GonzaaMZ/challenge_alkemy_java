package com.challengealkemy.challengealkemy.controllers;


import javax.validation.Valid;

import com.challengealkemy.challengealkemy.dto.PersonajeDTO;
import com.challengealkemy.challengealkemy.dto.PersonajeResponse;
import com.challengealkemy.challengealkemy.service.PersonajeService;
import com.challengealkemy.challengealkemy.utils.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class PersonajesController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> obtenerPersonajeById(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(personajeService.obtenerPersonajeById(id));
    }

    @GetMapping
    public PersonajeResponse obtenerPersonajes(
            @RequestParam(value = "nombre", required = false, defaultValue = Constantes.FILTRO_NOMBRE_POR_DEFECTO) String nombre, 
            @RequestParam(value = "edad", required = false, defaultValue = Constantes.FILTRO_EDAD_POR_DEFECTO) int edad,
            @RequestParam(value = "idMovie", required = false, defaultValue = Constantes.FILTRO_PELICULA_POR_DEFECTO) long idMovie)
            {
        return personajeService.obtenerPersonajes(nombre, edad, idMovie);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> guardarPersonaje(@Valid @RequestBody PersonajeDTO personajeDTO) {

        return new ResponseEntity<PersonajeDTO>(personajeService.crearPersonaje(personajeDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> actualizarPersonaje(@Valid @RequestBody PersonajeDTO personajeDTO,
            @PathVariable(name = "id") long id) {

        PersonajeDTO personajeActualizado = personajeService.actualizarPersonaje(personajeDTO, id);
        return new ResponseEntity<>(personajeActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonajeDTO> eliminarPersonaje(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(personajeService.eliminarPersonaje(id));
    }

}
