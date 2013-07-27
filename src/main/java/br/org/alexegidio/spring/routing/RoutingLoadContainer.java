package br.org.alexegidio.spring.routing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RoutingLoadContainer {

	public static void main(String[] args) {
		ApplicationContext c1 = new ClassPathXmlApplicationContext("rabbit-direct-routing-context.xml");

	}
}
