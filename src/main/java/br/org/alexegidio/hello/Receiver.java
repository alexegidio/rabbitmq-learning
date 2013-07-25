package br.org.alexegidio.hello;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException,
			InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		boolean durable = false;
		boolean exclusive = false;
		boolean autoDelete = false;

		channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		boolean autoAck = true;

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");
		}
	}
}
