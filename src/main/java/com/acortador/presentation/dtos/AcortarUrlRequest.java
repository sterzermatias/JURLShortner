package com.acortador.presentation.dtos;

import jakarta.validation.constraints.NotBlank;

public class AcortarUrlRequest {

    @NotBlank(message = "La URL original no puede estar vacía.")
    private String urlOriginal;

    private String codigoPersonalizado; // opcional

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getCodigoPersonalizado() {
        return codigoPersonalizado;
    }

    public void setCodigoPersonalizado(String codigoPersonalizado) {
        this.codigoPersonalizado = codigoPersonalizado;
    }
}
