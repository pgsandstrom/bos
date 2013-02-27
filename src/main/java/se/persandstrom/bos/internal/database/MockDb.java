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

    private Map<String, Entry> entryMap;
    private LinkedList<Entry> latest;

    private Random random;

    public MockDb() {
        entryMap = new HashMap<String, Entry>();
        latest = new LinkedList<Entry>();
        random = new Random();

        for (int i = 0; i < 15; i++) {
            post(new Entry("item " + i, i));
        }

        logger.info("mockdb created");
    }

    @Override
    public List<Entry> getLatest() {
        return latest;
    }

    @Override
    public int getCount() {
        return entryMap.size();
    }

    @Override
    public Entry get(String id) {
//		logger.info("getting " + id);
        Entry entry = entryMap.get(id);
        return entry;
    }

    @Override
    public Entry post(Entry entry) {
//		logger.info("saving " + entry.getKey() + ", " + entry.getContent());
        synchronized (latest) {
            latest.add(entry);
            if (latest.size() > LATEST_SIZE) {
                latest.remove();
            }
        }
        entryMap.put(entry.getKey(), entry);
        return entry;
    }

    @Override
    public Entry delete(Entry entry) {
        Entry removedEntry = entryMap.get(entry.getKey());
        entryMap.remove(entry.getKey());
        latest.remove(entry);
        return removedEntry;
    }

}
