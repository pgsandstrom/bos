package se.persandstrom.bos.internal.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.persandstrom.bos.internal.database.DbInterface;
import se.persandstrom.bos.internal.exception.InvalidDataException;

@Service
public class BosApi {

	private DbInterface database;

	public BosApi() {
	}
	
	@Autowired
	public BosApi(DbInterface database) {
		this.database = database;
	}
	
	public DbInterface getDb() {
		//for test only...
		return database;
	}

	public List<Entry> getLatest() {
		return database.getLatest();
	}

	public Entry getRandom() {
		return database.getRandom();
	}

	public Entry get(String id) {
		return database.get(id);
	}
	
	public Entry post(Entry entry) throws InvalidDataException {
		
		if(entry == null || entry.getContent() == null || "".equals(entry.getContent().trim())) {
			throw new InvalidDataException("Content must be a non-empty string");
		}
		
		return database.post(entry);
	}
}
