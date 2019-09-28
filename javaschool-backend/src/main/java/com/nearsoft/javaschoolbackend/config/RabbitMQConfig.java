package com.nearsoft.javaschoolbackend.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    private final ConfigProperties configProperties;

    @Bean
    Queue queue() {
        return new Queue(configProperties.getQueue(), false);
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(configProperties.getExchange());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topic) {
        return BindingBuilder.bind(queue).to(topic).with(configProperties.getRoutingKey());
    }

}
