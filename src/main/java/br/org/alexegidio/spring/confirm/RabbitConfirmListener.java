package br.org.alexegidio.spring.confirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitConfirmListener implements  MessageListener {

	public void onMessage(Message message) {
		String messageBody = new String(message.getBody());
		System.out.println("Message received processing juice----->" + messageBody);
		
	}
	
}
