package com.acortador.infrastructure.services;

import com.acortador.domain.services.GeneradorCodigoStrategy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GeneradorCodigoAleatorio implements GeneradorCodigoStrategy {

    @Override
    public String generar() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
