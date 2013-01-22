package se.persandstrom.bos.internal.api;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BosApiTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-configuration.xml");
		BeanFactory factory = context;
		BosApi bosApi = (BosApi) factory.getBean("BosApi");
		Entry random = bosApi.getRandom();
		System.out.println(random.getContent());
	}

}
