package com.acortador.domain.ports;
import com.acortador.domain.entities.Url;

import java.util.Optional;

public interface UrlRepository {
    Optional<Url> obtenerPorCodigo(String codigo);
    Optional<Url> obtenerPorUrlOriginal(String urlOriginal);
    void guardar(Url url);
}
