package se.persandstrom.bos.internal.database;

import java.util.List;

import se.persandstrom.bos.internal.api.Entry;

public interface DbInterface {

	public List<Entry> getLatest();

	public Entry getRandom();
}
