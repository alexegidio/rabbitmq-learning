package br.org.alexegidio.spring.route;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringNewsListener implements MessageListener {

	public void onMessage(Message message) {
		String messageBody = new String(message.getBody());
		System.out.println("NewsListener received message----->" + messageBody);
	}
}
