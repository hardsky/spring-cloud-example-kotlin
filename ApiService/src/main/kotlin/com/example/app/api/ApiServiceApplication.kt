package com.example.app.api

import com.example.app.api.messaging.VerificationReceiver
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import feign.Logger
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment


@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
class ApiServiceApplication(@Autowired val env: Environment) {

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun queue(): Queue {
        return Queue(env.getProperty("api.messaging.queue-name"), false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(env.getProperty("api.messaging.topic-exchange-name"))
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(env.getProperty("api.messaging.routing.scope"))
    }

    @Bean
    fun listenerAdapter(receiver: VerificationReceiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveVerification")
    }

    @Bean
    fun container(connectionFactory: ConnectionFactory,
                  listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        return SimpleMessageListenerContainer().apply {
            setConnectionFactory(connectionFactory)
            setQueueNames(env.getProperty("api.messaging.queue-name"))
            setMessageListener(listenerAdapter)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<ApiServiceApplication>(*args)
}
