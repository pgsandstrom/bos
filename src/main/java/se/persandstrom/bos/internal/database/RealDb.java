package se.persandstrom.bos.internal.database;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.exception.DataNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class RealDb implements DbInterface {

    private static final String PRE = "bos_";
    private static final String ITEM = PRE + "i_";
    private static final String LATEST = PRE + "latest";
    private static final String COUNT = PRE + "count";

    private final JedisPool jedisPool;

    public RealDb() throws IOException {

        //XXX is this really the nicest way to do this? I feel dirty.
        InputStream inputStream = RealDb.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String ip = properties.getProperty("redis.ip");
        int port = Integer.valueOf(properties.getProperty("redis.port"));
        String password = properties.getProperty("redis.password");

        //TODO if the connection to the database dies, we need to recreate it!
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(jedisPoolConfig, ip, port, 0, password);
    }

    private Jedis getConnection() {
        Jedis jedis = jedisPool.getResource();
//        jedis.auth("the_password");
        return jedis;
    }


    private void returnConnection(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }

    @Override
    public List<Entry> getLatest() {

        /**
         * TODO this system is bad. If an entry with 1000000 chars is set, then it will be loaded entirely when the
         * "latest" list is loaded. Instead, just save the first 100 chars and the hash.
         */

        Jedis jedis = getConnection();
        List<String> latest = jedis.lrange(LATEST, 0, LATEST_SIZE);
        List<Entry> latestEntries = new ArrayList<Entry>(latest.size());
        for (String latestContent : latest) {
            latestEntries.add(new Entry(latestContent));
        }
        returnConnection(jedis);
        return latestEntries;
    }

    @Override
    public int getCount() {
        Jedis jedis = getConnection();
        Integer count = Integer.valueOf(jedis.get(COUNT));
        returnConnection(jedis);
        return count;

    }

    @Override
    public Entry get(String id) {
        Jedis jedis = getConnection();
        String content = jedis.get(id);
        returnConnection(jedis);
        return new Entry(content);
    }

    @Override
    public Entry post(Entry entry) {
        Jedis jedis = getConnection();
        if (!jedis.exists(entry.getKey())) {
            jedis.incr(COUNT);
        }
        jedis.set(ITEM + entry.getKey(), entry.getContent());
        jedis.lpush(LATEST, entry.getContent());
        jedis.ltrim(LATEST, 0, LATEST_SIZE);
        returnConnection(jedis);
        return entry;
    }

    @Override
    public Entry delete(Entry entry) throws DataNotFoundException {
        Jedis jedis = getConnection();

        Entry removingEntry = get(entry.getKey());
        if (removingEntry == null) {
            throw new DataNotFoundException("not found");
        }

        jedis.lrem(LATEST, 0, removingEntry.getContent());

        Long count = jedis.del(entry.getKey());
        if (count != null && count > 0) {
            jedis.decr(COUNT);
        }
        returnConnection(jedis);

        return removingEntry;
    }
}
