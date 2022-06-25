package com.challengealkemy.challengealkemy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private String nombreRecurso;

    @Getter @Setter
    private String nombreCampo;

    @Getter @Setter
    private long valorCampo;

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, long valorCampo){
        super(String.format("%s no encontrada con : %s : '%s'", nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }

}
