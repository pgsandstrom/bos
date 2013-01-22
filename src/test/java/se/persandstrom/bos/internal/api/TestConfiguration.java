package se.persandstrom.bos.internal.api;

import org.springframework.context.annotation.Bean;

import se.persandstrom.bos.internal.database.DbInterface;
import se.persandstrom.bos.internal.database.RealDb;

//@Configuration
public class TestConfiguration {

	@Bean
	public DbInterface myService() {
//		return new MockDb();
		return new RealDb();
	}
}
