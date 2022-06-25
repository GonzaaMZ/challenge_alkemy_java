package com.challengealkemy.challengealkemy.security;

import lombok.Data;

@Data
public class JwtAuthResponseDTO {

    private String tokenAcceso;

    private String tipoToken = "Bearer";

    public JwtAuthResponseDTO(String tokenAcceso, String tipoToken) {
        this.tokenAcceso = tokenAcceso;
        this.tipoToken = tipoToken;
    }

    public JwtAuthResponseDTO(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

}
