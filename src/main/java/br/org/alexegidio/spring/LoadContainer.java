package br.org.alexegidio.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class LoadContainer {

	public static void main(String[] args) {
		ApplicationContext c1 = new ClassPathXmlApplicationContext("rabbit-listener-context.xml");

	}
}
