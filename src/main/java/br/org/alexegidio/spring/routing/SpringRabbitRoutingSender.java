package br.org.alexegidio.spring.routing;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRabbitRoutingSender {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("rabbit-sender-context.xml");
		AmqpTemplate aTemplate = (AmqpTemplate) context.getBean("routingTemplate");

		for (int i = 0; i < 10; i++) {
			int rest = (i % 2);
			if (rest == 1) {
				aTemplate.convertAndSend("apple", "Apple # " + i + " on " + new Date());
			} else {
				aTemplate.convertAndSend("orange", "Orange # " + i + " on " + new Date());
			}
		}
	}
}
