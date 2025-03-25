package com.acortador.infrastructure.messaging.event;

import java.io.Serializable;

public class UrlAcortadaEvent implements Serializable {

    private String id;
    private String urlOriginal;
    private String urlAcortada;

    public UrlAcortadaEvent() {}

    public UrlAcortadaEvent(String id, String urlOriginal, String urlAcortada) {
        this.id = id;
        this.urlOriginal = urlOriginal;
        this.urlAcortada = urlAcortada;
    }

    public String getId() {
        return id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public String getUrlAcortada() {
        return urlAcortada;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public void setUrlAcortada(String urlAcortada) {
        this.urlAcortada = urlAcortada;
    }
}
