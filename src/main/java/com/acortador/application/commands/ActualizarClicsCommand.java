package com.acortador.application.commands;

public class ActualizarClicsCommand {
    private final String codigo;

    public ActualizarClicsCommand(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}