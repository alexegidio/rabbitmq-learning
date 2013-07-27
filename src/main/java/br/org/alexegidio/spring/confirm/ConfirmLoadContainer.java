package br.org.alexegidio.spring.confirm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConfirmLoadContainer {

	public static void main(String[] args) {
		ApplicationContext c1 = new ClassPathXmlApplicationContext("rabbit-confirm-context.xml");

	}
}
