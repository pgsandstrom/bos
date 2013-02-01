package se.persandstrom.bos.internal.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import se.persandstrom.bos.internal.api.Entry;

@Service
public class RealDb implements DbInterface {

	//TODO make this a real db

	@Override
	public List<Entry> getLatest() {
		//temp:
		ArrayList<Entry> list = new ArrayList<Entry>();
		list.add(new Entry("1970 something REAL", 0));
		list.add(new Entry("current REAL", System.currentTimeMillis()));
		return list;
	}

	@Override
	public Entry getRandom() {
		return new Entry("random entry REAL", new Random().nextLong());
	}

	@Override
	public Entry get(String id) {
		return new Entry(id + " REAL", new Random().nextLong());
	}

	@Override
	public Entry post(Entry entry) {
		return entry;
	}

    @Override
    public void delete(Entry entry) {
    }
}
