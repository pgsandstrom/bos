package se.persandstrom.bos.internal.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.persandstrom.bos.internal.database.DbInterface;

@Service
public class BosApi {

	private DbInterface database;

	public BosApi() {
	}

	@Autowired
	public void setDatabase(DbInterface database) {
		this.database = database;
	}

	public List<Entry> getLatest() {
		return database.getLatest();
	}

	public Entry getRandom() {
		return database.getRandom();
	}
}
