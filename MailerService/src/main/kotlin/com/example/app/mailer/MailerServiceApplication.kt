package com.example.app.mailer

import com.example.app.mailer.messaging.Receiver
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment


@SpringBootApplication
class MailerServiceApplication(
	@Autowired
	private val env: Environment
) {

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
	fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
		return MessageListenerAdapter(receiver, "receiveMessage")
	}

	@Bean
	fun container(
		connectionFactory: ConnectionFactory,
		listenerAdapter: MessageListenerAdapter
	): SimpleMessageListenerContainer? {
		val container = SimpleMessageListenerContainer()
		container.connectionFactory = connectionFactory
		container.setQueueNames(env.getProperty("api.messaging.queue-name"))
		container.setMessageListener(listenerAdapter)
		return container
	}
}

fun main(args: Array<String>) {
	runApplication<MailerServiceApplication>(*args)
}
