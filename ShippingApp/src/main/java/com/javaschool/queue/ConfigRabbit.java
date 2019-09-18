package com.javaschool.queue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.common.GlobalProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbit {

    private GlobalProperties globalProperties;

    public ConfigRabbit(final GlobalProperties globalProperties){
        this.globalProperties = globalProperties;
    }

    @Bean
    public Queue shippingQueue() {
        return new Queue(globalProperties.getQueueShipping(), false);
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(globalProperties.getExchangeShipping());
    }

    @Bean
    public Binding shippingBinding(TopicExchange topic, Queue queue) {
        return BindingBuilder.bind(queue).to(topic).with(globalProperties.getRoutingKeyShipping());
    }
}
