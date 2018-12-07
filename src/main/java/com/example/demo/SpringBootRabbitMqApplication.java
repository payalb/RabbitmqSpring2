package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.consumer.ConsumerOne;
import com.example.demo.producer.ProducerOne;
import com.example.demo.util.RabbitAmqpTutorialsRunner;
/*In the first example we wrote programs to send and receive messages from a named queue. In this one we'll create a Work Queue that will be used to distribute time-consuming tasks among multiple workers.
*/@SpringBootApplication
@EnableScheduling
public class SpringBootRabbitMqApplication {
	
	@Bean
	public Queue queue() {
		return new Queue("queue1");
	}
	
	@Bean
	@Profile("Sender")
	public ProducerOne producer() {
		return new ProducerOne();
	}
	@Bean
	@Profile("Receiver")
	public ConsumerOne consumer1() {
		return new ConsumerOne(1);
	}
	
	@Bean
	@Profile("Receiver")
	public ConsumerOne consumer2() {
		return new ConsumerOne(2);
	}
	@Profile("usage_message")
    @Bean
    public CommandLineRunner usage() {
        return args -> {
            System.out.println("This app uses Spring Profiles to control its behavior.\n");
            System.out.println("Sample usage: java -jar  rabbit-one.jar --spring.profiles.active=hello-world,sender");
        };
    }

	@Profile("!usage_message")
    @Bean
    public CommandLineRunner tutorial() {
        return new RabbitAmqpTutorialsRunner();
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitMqApplication.class, args);
	}
}
