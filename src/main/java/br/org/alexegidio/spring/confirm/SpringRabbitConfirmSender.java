package br.org.alexegidio.spring.confirm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRabbitConfirmSender {

	private static Map<Integer, String> messageSource = getMessageSourceInstance();

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("rabbit-sender-context.xml");
		RabbitTemplate aTemplate = (RabbitTemplate) context.getBean("confirmTemplate");

		aTemplate.setMandatory(true);
		aTemplate.setReturnCallback(new ReturnCallback() {

			public void returnedMessage(Message message, int replyCode, String replyText, String exchange,
					String routingKey) {
				System.out.println("Message " + message + "confirmed" + "reply" + replyCode);
			}
		});

		for (int i = 0; i < 10; i++) {
			String message = new String("Message # " + i + " on ");
			aTemplate.convertAndSend("confirm", message + new Date());
			getMessageSourceInstance().put(i, message);
		}
	}

	public static Map<Integer, String> getMessageSourceInstance() {
		if (messageSource == null) {
			messageSource = new HashMap<Integer, String>();
		}
		return messageSource;
	}

}
