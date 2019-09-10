package com.javaschool.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
        return new Queue(QUEUE_SHIPPING, false);
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(EXCHANGE_SHIPPING);
    }

    @Bean
    public Binding shippingBinding(TopicExchange topic, Queue queue) {
        return BindingBuilder.bind(queue).to(topic).with(ROUTING_KEY_SHIPPING);
    }
}
