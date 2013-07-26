package br.org.alexegidio.spring;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRabbitSender {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("rabbit-sender-context.xml");// loading beans
		AmqpTemplate aTemplate = (AmqpTemplate) context.getBean("tutorialTemplate");// getting a reference to the sender
																					// bean

		for (int i = 0; i < 10; i++)
			aTemplate.convertAndSend("my.routingkey.1", "Message # " + i + " on " + new Date());// send
	}
}
