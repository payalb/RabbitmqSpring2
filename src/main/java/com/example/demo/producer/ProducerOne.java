package com.example.demo.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/*Now we'll be sending strings that stand for complex tasks. We don't have a real-world task, like images to be resized or PDF files to be rendered, so let's fake it by just pretending we're busy - by using the Thread.sleep() function. We'll take the number of dots in the string as its complexity; every dot will account for one second of "work". For example, a fake task described by Hello... will take three seconds.*/
public class ProducerOne {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		String message = "Hello World!";
	/*	"Convert a Java object to an AMQP Message and send it to a default exchange with a default routing key."*/
		this.template.convertAndSend(queue.getName(), message);
		System.out.println(" [x] Sent '" + message + "'");
	}

}
