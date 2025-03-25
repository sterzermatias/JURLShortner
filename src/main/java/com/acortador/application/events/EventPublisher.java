package com.acortador.application.events;

public interface EventPublisher<T> {
    void publish(T event);
}
