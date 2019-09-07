package com.javaschool.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbit {

    @Value("${config-rabbit.queue}")
    public String QUEUE_SHIPPING;

    @Value("${config-rabbit.exchange}")
    public String EXCHANGE_SHIPPING;

    @Value("${config-rabbit.routing-key}")
    public String ROUTING_KEY_SHIPPING;

    @Bean
    public Queue shippingQueue() {
        return new Queue(QUEUE_SHIPPING);
    }

    @Bean
    public DirectExchange shippingExchange() {
        return new DirectExchange(EXCHANGE_SHIPPING);
    }

    @Bean
    public Binding shippingBinding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY_SHIPPING);
    }
}
