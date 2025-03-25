package com.acortador.infrastructure.messaging.publisher;

import com.acortador.application.events.EventPublisher;
import com.acortador.infrastructure.messaging.event.UrlAcortadaEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQEventPublisher implements EventPublisher<UrlAcortadaEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public RabbitMQEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(UrlAcortadaEvent event) {
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
