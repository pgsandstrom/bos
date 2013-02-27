package se.persandstrom.bos.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.persandstrom.bos.internal.api.BosApi;
import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.database.MockDb;
import se.persandstrom.bos.internal.exception.DataNotFoundException;
import se.persandstrom.bos.internal.exception.InvalidDataException;

import static org.junit.Assert.*;

/**
 * @author pesandst
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
    public void testGetLatest() throws InvalidDataException {
        List<Entry> latest = bosApi.getLatest();
        int count = bosApi.getCount();
        assertEquals(latest.isEmpty(), count == 0);

        Entry entry = bosApi.post(new Entry("test"));
        List<Entry> newLatest = bosApi.getLatest();
        assertTrue(newLatest.contains(entry));
    }

    @Test
    public void testAddGet() throws InvalidDataException {
        Entry test = bosApi.post(new Entry("test"));
        Entry entryCopy = bosApi.get(test.getKey());
        assertEquals(test, entryCopy);
    }

    @Test
    public void testAddInvalidData(){
        try {
            bosApi.post(new Entry(""));
            assertTrue(false);
        } catch (InvalidDataException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testRemove() throws InvalidDataException, DataNotFoundException {

        int prevCount = bosApi.getCount();
        Entry test = bosApi.post(new Entry("test"));
        bosApi.delete(test);

        assertNull(bosApi.get(test.getKey()));
    }

    @Test
    public void testCount() throws InvalidDataException, DataNotFoundException {

        int prevCount = bosApi.getCount();
        Entry test = bosApi.post(new Entry("test"));

        int largerCount = bosApi.getCount();

        assertTrue(largerCount>prevCount);

        bosApi.delete(test);
    }

    /*@Test
    public void testGetRandom() {
        Entry random = bosApi.getRandom();
        int count = bosApi.getCount();
        assertEquals(random != null, count > 0);
    }*/

}
