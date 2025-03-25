package com.acortador.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public class CodigoURL implements Serializable {
    private final String valor;

    public CodigoURL(String valor) {
        if (valor == null || valor.length() < 6) {
            throw new IllegalArgumentException("El código debe tener al menos 6 caracteres.");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoURL)) return false;
        CodigoURL that = (CodigoURL) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
