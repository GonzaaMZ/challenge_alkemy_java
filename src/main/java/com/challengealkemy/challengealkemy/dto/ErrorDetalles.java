package com.challengealkemy.challengealkemy.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDetalles {
    private Date marcaDeTiempo;

    private String mensaje;

    private String detalles;

    public ErrorDetalles(Date marcaDeTiempo, String mensaje, String detalles) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
}
