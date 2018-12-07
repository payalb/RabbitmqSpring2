java -jar target/rabbit-one.jar --spring.profiles.active=Sender,usage_message


java -jar target/rabbit-one.jar --spring.profiles.active=Receiver,usage_message


https://www.rabbitmq.com/tutorials/tutorial-two-spring-amqp.html


we knew nothing about exchanges, but still were able to send messages to queues. That was possible because we were using a default exchange, which we identify by the empty string ("").
Recall how we published a message before:

   template.convertAndSend(queue.getName(), message)

The first parameter is the routing key and the RabbitTemplate sends messages by default to the default exchange. Each queue is automatically bound to the default exchange with the name of queue as the binding key. This is why we can use the name of the queue as the routing key to make sure the message ends up in the queue.
