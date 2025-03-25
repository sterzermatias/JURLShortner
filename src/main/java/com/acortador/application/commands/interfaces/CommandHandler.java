package com.acortador.application.commands.interfaces;

public interface CommandHandler<C, R> {
    R handle(C command);
}
