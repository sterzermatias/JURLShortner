package com.acortador.infrastructure.repositories;

import com.acortador.domain.entities.Url;
import com.acortador.domain.ports.UrlRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisUrlRepository implements UrlRepository {

    private final RedisTemplate<String, Url> redisTemplateUrl;
    private final RedisTemplate<String, String> redisTemplateString;
    private static final String PREFIX_URL = "url:";
    private static final String PREFIX_ORIGINAL = "original:";

    public RedisUrlRepository(
            RedisTemplate<String, Url> redisTemplateUrl,
            RedisTemplate<String, String> redisTemplateString
    ) {
        this.redisTemplateUrl = redisTemplateUrl;
        this.redisTemplateString = redisTemplateString;
    }

    @Override
    public Optional<Url> obtenerPorCodigo(String codigo) {
        Url url = redisTemplateUrl.opsForValue().get(PREFIX_URL + codigo);
        return Optional.ofNullable(url);
    }

    @Override
    public Optional<Url> obtenerPorUrlOriginal(String urlOriginal) {
        String codigo = redisTemplateString.opsForValue().get(PREFIX_ORIGINAL + urlOriginal);
        if (codigo == null) return Optional.empty();
        return obtenerPorCodigo(codigo);
    }

    @Override
    public void guardar(Url url) {
        String code = url.getCodigoCorto().getValor();
        String urlOriginal = url.getUrlOriginal();

        redisTemplateUrl.opsForValue().set(PREFIX_URL + code, url);

        redisTemplateString.opsForValue().set(PREFIX_ORIGINAL + urlOriginal, code);
    }
}
