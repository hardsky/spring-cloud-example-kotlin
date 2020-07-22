package com.example.app.approver

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@SpringBootApplication
class ApproverServiceApplication(@Autowired val env: Environment){
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
}

fun main(args: Array<String>) {
	runApplication<ApproverServiceApplication>(*args)
}
