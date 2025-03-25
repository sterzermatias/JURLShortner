package com.acortador.application.commands;

import com.acortador.application.commands.interfaces.CommandHandler;
import com.acortador.application.events.EventPublisher;
import com.acortador.domain.entities.Url;
import com.acortador.domain.ports.UrlRepository;
import com.acortador.domain.services.GeneradorCodigoStrategy;
import com.acortador.domain.valueobjects.CodigoURL;
import com.acortador.infrastructure.messaging.event.UrlAcortadaEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AcortarURLCommandHandler implements CommandHandler<AcortarURLCommand, String> {

    private final UrlRepository urlRepository;
    private final GeneradorCodigoStrategy generadorCodigo;
    private final EventPublisher<UrlAcortadaEvent> eventPublisher;

    @Value("${app.dominio}")
    private String dominio;

    public AcortarURLCommandHandler(UrlRepository urlRepository, GeneradorCodigoStrategy generadorCodigo,
                                    EventPublisher<UrlAcortadaEvent> eventPublisher) {
        this.urlRepository = urlRepository;
        this.generadorCodigo = generadorCodigo;
        this.eventPublisher = eventPublisher;

    }

    @Override
    public String handle(AcortarURLCommand command) {
        Optional<Url> existente = urlRepository.obtenerPorUrlOriginal(command.getUrlOriginal());
        if (existente.isPresent()) {
            return existente.get().getCodigoCorto().getValor();
        }

        String codigoFinal = (command.getCodigoPersonalizado() != null && !command.getCodigoPersonalizado().isBlank())
                ? command.getCodigoPersonalizado()
                : generadorCodigo.generar();

        if (urlRepository.obtenerPorCodigo(codigoFinal).isPresent()) {
            throw new IllegalArgumentException("El código ya está en uso.");
        }

        Url url = new Url(command.getUrlOriginal(), new CodigoURL(codigoFinal));
        urlRepository.guardar(url);

        String urlAcortada = dominio + "/" + url.getCodigoCorto().getValor();

        UrlAcortadaEvent event = new UrlAcortadaEvent(
                codigoFinal,
                url.getUrlOriginal(),
                urlAcortada
        );

        System.out.println("📤 Publicando evento a RabbitMQ: " + event);

        eventPublisher.publish(event);

        return codigoFinal;
    }
}
