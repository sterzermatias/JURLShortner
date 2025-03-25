package com.acortador.application.queries.interfaces;

public interface QueryHandler<Q, R> {
    R handle(Q query);
}
