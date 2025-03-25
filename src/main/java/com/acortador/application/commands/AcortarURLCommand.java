package com.acortador.application.commands;

public class AcortarURLCommand {
    private final String urlOriginal;
    private final String codigoPersonalizado;

    public AcortarURLCommand(String urlOriginal, String codigoPersonalizado) {
        this.urlOriginal = urlOriginal;
        this.codigoPersonalizado = codigoPersonalizado;
    }

    public String getUrlOriginal() { return urlOriginal; }
    public String getCodigoPersonalizado() { return codigoPersonalizado; }
}