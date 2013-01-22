package se.persandstrom.bos.internal.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.persandstrom.bos.internal.api.Entry;

//@Service
public class RealDb implements DbInterface {

	@Override
	public List<Entry> getLatest() {
		//temp:
		ArrayList<Entry> list = new ArrayList<Entry>();
		list.add(new Entry(0, "1970 something MOCK"));
		list.add(new Entry(System.currentTimeMillis(), "current MOCK"));
		return list;
	}

	@Override
	public Entry getRandom() {
		return new Entry(new Random().nextLong(), "random entry MOCK");
	}
}
