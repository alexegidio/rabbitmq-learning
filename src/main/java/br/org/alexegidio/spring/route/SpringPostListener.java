package br.org.alexegidio.spring.route;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringPostListener implements MessageListener {

	public void onMessage(Message message) {
		String messageBody = new String(message.getBody());
		System.out.println("PostListener received message----->" + messageBody);
	}
	
}
