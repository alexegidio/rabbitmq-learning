package br.org.alexegidio.spring.confirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class RabbitConfirmListener implements  ChannelAwareMessageListener{


	public void onMessage(Message message, Channel channel) throws Exception {
		String messageBody = new String(message.getBody());
		System.out.println("Message received processing juice----->" + messageBody);
		
		//New messages wont be received until the listener ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		
	}
	
}
