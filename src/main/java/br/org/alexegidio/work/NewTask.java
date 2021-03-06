package br.org.alexegidio.work;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] args) throws IOException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		for (Integer i = 1; i < 10; i++) {
			String message = getMessage(i.toString());

			channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");
		}

		channel.close();
		connection.close();
	}

	private static String getMessage(String strings) {
		if (strings.length() < 1) {
			return "Hello World!";
		}
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String strings, String delimiter) {
		int length = strings.length();
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings);
		}
		return words.toString();
	}

}
