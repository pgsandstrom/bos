package se.persandstrom.bos.internal.database;

import java.util.List;

import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.exception.DataNotFoundException;

public interface DbInterface {

	public int LATEST_SIZE = 10;
	
	public List<Entry> getLatest();

    public int getCount();
	
	public Entry get(String id);

    public Entry post(Entry entry);

    public Entry delete(Entry entry) throws DataNotFoundException;
}
