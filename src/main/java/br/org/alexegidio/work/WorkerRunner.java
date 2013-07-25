package br.org.alexegidio.work;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * Demonstra o funcionamento de workers e mensagens persistentes
 * 
 * @project br.org.alexegidio.work
 * 
 * @author cad_aegidio@uolinc.com
 * 
 * @since 25/07/2013
 * 
 * @definition:
 */
public class WorkerRunner {

	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException,
			InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		boolean durable = true;
		boolean exclusive = false;
		boolean autoDelete = false;

		channel.queueDeclare(TASK_QUEUE_NAME, durable, exclusive, autoDelete, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		channel.basicQos(1); // Indica que não será enviada outra msg consumer enquanto nao houver um ack

		boolean autoAck = false;

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);

		while (true) {

			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());

			System.out.println(" [x] Received '" + message + "'");
			doWork(message);
			System.out.println(" [x] Done");

			boolean multiple = false;

			Thread.sleep(3000);
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), multiple);
		}
	}

	private static void doWork(String task) throws InterruptedException {
		for (char ch : task.toCharArray()) {
			if (ch == '.')
				Thread.sleep(1000);
		}
	}

}
