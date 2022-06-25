package com.challengealkemy.challengealkemy.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class AppException extends RuntimeException {

    private static final long serialVersion = 1L;

    private HttpStatus estado;

    private String mensaje;

    public AppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public AppException(HttpStatus estado, String mensaje, String mensaje1) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }

}
