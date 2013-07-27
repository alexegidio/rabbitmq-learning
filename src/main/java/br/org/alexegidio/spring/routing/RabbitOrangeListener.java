package br.org.alexegidio.spring.routing;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitOrangeListener implements MessageListener {

	public void onMessage(Message message) {
		String messageBody = new String(message.getBody());
		System.out.println("Orange received processing juice----->" + messageBody);
	}
	
}
