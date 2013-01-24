package se.persandstrom.bos.internal.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.persandstrom.bos.internal.api.Entry;

@Service
public class MockDb implements DbInterface {

	static final Logger logger = LoggerFactory.getLogger(MockDb.class);

	Map<String, Entry> entryMap;
	LinkedList<Entry> latest;

	Random random;

	public MockDb() {
		entryMap = new HashMap<String, Entry>();
		latest = new LinkedList<Entry>();
		random = new Random();
	}

	@Override
	public List<Entry> getLatest() {
		return latest;
	}

	@Override
	public Entry getRandom() {
		if (entryMap.isEmpty()) {
			return null;
		}

		List<String> keys = new ArrayList<String>(entryMap.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		Entry entry = entryMap.get(randomKey);
		return entry;
	}

	@Override
	public Entry get(String id) {
		logger.info("getting " + id);
		Entry entry = entryMap.get(id);
		if(entry != null) {
			logger.info("got " + entry.getContent());			
		} else {
			logger.info("got null");
		}
		return entry;
	}

	@Override
	public Entry post(Entry entry) {
		logger.info("saving " + entry.getKey() + ", " + entry.getContent());
		synchronized (latest) {
			latest.add(entry);
			if (latest.size() > LATEST_SIZE) {
				latest.remove();
			}
		}
		entryMap.put(entry.getKey(), entry);
		return entry;
	}

}
