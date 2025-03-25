package com.acortador.presentation.dtos;

import java.time.LocalDateTime;

public class UrlStatsResponse {

    private String codigoCorto;
    private String urlOriginal;
    private int clics;
    private LocalDateTime fechaCreacion;

    public UrlStatsResponse(String codigoCorto, String urlOriginal, int clics, LocalDateTime fechaCreacion) {
        this.codigoCorto = codigoCorto;
        this.urlOriginal = urlOriginal;
        this.clics = clics;
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigoCorto() {
        return codigoCorto;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public int getClics() {
        return clics;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
