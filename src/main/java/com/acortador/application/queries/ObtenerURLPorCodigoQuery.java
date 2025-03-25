package com.acortador.application.queries;

public class ObtenerURLPorCodigoQuery {
    private final String codigo;

    public ObtenerURLPorCodigoQuery(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}