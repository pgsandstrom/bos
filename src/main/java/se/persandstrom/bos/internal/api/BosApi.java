package se.persandstrom.bos.internal.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.persandstrom.bos.internal.database.DbInterface;
import se.persandstrom.bos.internal.exception.InvalidDataException;

import java.util.List;

@Service
public class BosApi {

    static final Logger logger = LoggerFactory.getLogger(BosApi.class);

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

        if (entry == null || entry.getContent() == null || "".equals(entry.getContent().trim())) {
            logger.warn("received non-ok post. "
                    + (entry == null ? "entry: " + entry : "content: " + entry.getContent()));
            throw new InvalidDataException("Content must be a non-empty string");
        }

        return database.post(entry);
    }

    public void delete(Entry entry) throws InvalidDataException {
        if(entry == null ||entry.getKey() == null) {
            logger.warn("received non-ok delete. "
                    + (entry == null ? "entry: " + entry : "key: " + entry.getKey()));
            throw new InvalidDataException("Please give a valid key");
        }

        database.delete(entry);
    }
}
