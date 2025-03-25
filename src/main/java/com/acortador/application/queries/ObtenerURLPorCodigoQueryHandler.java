package com.acortador.application.queries;

import com.acortador.application.queries.interfaces.QueryHandler;
import com.acortador.domain.entities.Url;
import com.acortador.domain.ports.UrlRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ObtenerURLPorCodigoQueryHandler implements QueryHandler<ObtenerURLPorCodigoQuery, Optional<Url>> {

    private final UrlRepository urlRepository;

    public ObtenerURLPorCodigoQueryHandler(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Optional<Url> handle(ObtenerURLPorCodigoQuery query) {
        return urlRepository.obtenerPorCodigo(query.getCodigo());
    }
}