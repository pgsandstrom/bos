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
		list.add(new Entry(0, "1970 something REAL"));
		list.add(new Entry(System.currentTimeMillis(), "current REAL"));
		return list;
	}

	@Override
	public Entry getRandom() {
		return new Entry(new Random().nextLong(), "random entry REAL");
	}

	@Override
	public Entry get(String id) {
		return new Entry(new Random().nextLong(), id+ " REAL");
	}

	@Override
	public Entry post(Entry entry) {
		return entry;
	}
}
