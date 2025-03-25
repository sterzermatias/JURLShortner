package com.acortador.application.commands;
import com.acortador.application.commands.interfaces.CommandHandler;
import com.acortador.domain.entities.Url;
import com.acortador.domain.ports.UrlRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActualizarClicsCommandHandler implements CommandHandler<ActualizarClicsCommand, Void> {

    private final UrlRepository urlRepository;

    public ActualizarClicsCommandHandler(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Void handle(ActualizarClicsCommand command) {
        Optional<Url> optionalUrl = urlRepository.obtenerPorCodigo(command.getCodigo());
        if (optionalUrl.isEmpty()) {
            throw new IllegalArgumentException("Código no encontrado.");
        }

        Url url = optionalUrl.get();
        url.incrementarClics();
        urlRepository.guardar(url);

        return null;
    }
}