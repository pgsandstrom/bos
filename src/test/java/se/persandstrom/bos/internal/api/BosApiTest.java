package se.persandstrom.bos.internal.api;

import se.persandstrom.bos.internal.database.MockDb;

public class BosApiTest {

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-configuration.xml");
//		BeanFactory factory = context;
//		BosApi bosApi = (BosApi) factory.getBean("BosApi");
		BosApi bosApi = new BosApi(new MockDb());
		Entry random = bosApi.getRandom();
		System.out.println(random.getContent());
	}

}
