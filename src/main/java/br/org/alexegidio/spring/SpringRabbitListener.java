package br.org.alexegidio.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringRabbitListener implements MessageListener {

	public void onMessage(Message message) {
		String messageBody = new String(message.getBody());
		System.out.println("Listener received message----->" + messageBody);
	}
}
