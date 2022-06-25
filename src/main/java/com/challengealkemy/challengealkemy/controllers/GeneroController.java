package com.challengealkemy.challengealkemy.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challengealkemy.challengealkemy.dto.GeneroDTO;
import com.challengealkemy.challengealkemy.service.GeneroService;

@RestController
@RequestMapping("/genre")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroDTO> crearGenero(@Valid @RequestBody GeneroDTO generoDTO){
        return new ResponseEntity<GeneroDTO>(generoService.crearGenero(generoDTO), HttpStatus.CREATED);
    }

}
