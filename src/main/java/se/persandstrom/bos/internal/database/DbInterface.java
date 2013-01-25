package se.persandstrom.bos.internal.database;

import java.util.List;

import se.persandstrom.bos.internal.api.Entry;

public interface DbInterface {

	public int LATEST_SIZE = 10;
	
	public List<Entry> getLatest();

	public Entry getRandom();
	
	public Entry get(String id);
	
	public Entry post(Entry entry);
}
