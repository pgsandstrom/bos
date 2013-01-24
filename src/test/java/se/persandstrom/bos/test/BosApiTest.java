/**
 * 
 */
package se.persandstrom.bos.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.persandstrom.bos.internal.api.BosApi;
import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.database.MockDb;

/**
 * @author pesandst
 * 
 */
public class BosApiTest {

	BosApi bosApi;

	@Before
	public void setUp() throws Exception {
		bosApi = new BosApi(new MockDb());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLatest() {
		List<Entry> latest = bosApi.getLatest();
		assertNotNull(latest);
	}

	@Test
	public void testGetRandom() {
		Entry random = bosApi.getRandom();
		if (random != null) {
			assertTrue(random.getCreatedTimeMs() > 0);
		}
	}

}
