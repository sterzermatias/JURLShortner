package com.acortador.domain.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import com.acortador.domain.valueobjects.CodigoURL;

public class Url implements Serializable {
    private final UUID id;
    private final String urlOriginal;
    private final CodigoURL codigoCorto;
    private int clics;
    private final LocalDateTime fechaCreacion;
    
    public Url(String urlOriginal, CodigoURL codigoCorto) {
        this.id = UUID.randomUUID();
        this.urlOriginal = urlOriginal;
        this.codigoCorto = codigoCorto;
        this.clics = 0;
        this.fechaCreacion = LocalDateTime.now();
    }
    public void incrementarClics() {
        this.clics++;
    }

    public UUID getId() { return id; }
    public String getUrlOriginal() { return urlOriginal; }
    public CodigoURL getCodigoCorto() { return codigoCorto; }
    public int getClics() { return clics; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
