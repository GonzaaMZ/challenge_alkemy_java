package com.challengealkemy.challengealkemy.controllers;

import java.util.List;

import javax.validation.Valid;

import com.challengealkemy.challengealkemy.dto.PeliculaDTO;
import com.challengealkemy.challengealkemy.service.PeliculaService;
import com.challengealkemy.challengealkemy.utils.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class PeliculasController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaById(@PathVariable(value = "id") long idPelicula) {
        return new ResponseEntity<PeliculaDTO>(peliculaService.obtenerPeliculaById(idPelicula), HttpStatus.OK);
    }

    @GetMapping
    public List<PeliculaDTO> obtenerPeliculas(@RequestParam(value = "titulo", required = false) String titulo,
                                            @RequestParam(value = "idGenre", required = false, defaultValue = Constantes.FILTRO_GENERO_POR_DEFECTO) long idGenero,
                                            @RequestParam(value = "order", required = false) String sortDir) {
        return peliculaService.obtenerPeliculas(titulo, idGenero, sortDir);
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> crearPelicula(@Valid @RequestBody PeliculaDTO peliculaDTO) {

        return new ResponseEntity<PeliculaDTO>(peliculaService.crearPelicula(peliculaDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizarPelicula(@Valid @RequestBody PeliculaDTO peliculaDTO,
            @PathVariable(name = "id") long id) {
                PeliculaDTO peliculaActualizada = peliculaService.actualizarPelicula(peliculaDTO, id);
                return new ResponseEntity<>(peliculaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PeliculaDTO> eliminarPelicula(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(peliculaService.eliminarPelicula(id));
    }

    @PostMapping("/{idMovie}/characters/{idCharacters}")
    public ResponseEntity<PeliculaDTO> añadirPersonaje(@PathVariable(value = "idMovie") long idPelicula,
                                                        @PathVariable(value = "idCharacters") long idPersonaje){
            PeliculaDTO peliculaDTO = peliculaService.añadirPersonaje(idPersonaje, idPelicula);
            return new ResponseEntity<>(peliculaDTO, HttpStatus.OK);
        }
    
    @DeleteMapping("/{idMovie}/characters/{idCharacters}")
    public ResponseEntity<PeliculaDTO> removerPersonaje(@PathVariable(value = "idMovie") long idPelicula,
                                                        @PathVariable(value = "idCharacters") long idPersonaje){
            PeliculaDTO peliculaDTO = peliculaService.removerPersonaje(idPersonaje, idPelicula);
            return new ResponseEntity<>(peliculaDTO, HttpStatus.OK);
        }

    
}
