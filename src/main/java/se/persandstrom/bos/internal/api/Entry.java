package se.persandstrom.bos.internal.api;

import org.apache.commons.codec.digest.DigestUtils;

public class Entry {

    //not saved in db currently
    private long createdTimeMs;

    private String content;

    private String key;

    public Entry() {
    }

    public Entry(String content) {
        this();
        this.content = content;
        generateKey();
    }

    public Entry(String content, String key) {
        this.key = key;
    }

    public Entry(String content, long createdTimeMs) {
        this(content);
        this.createdTimeMs = createdTimeMs;
    }

    public long getCreatedTimeMs() {
        return createdTimeMs;
    }

    public String getContent() {
        return content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCreatedTimeMs(long createdTimeMs) {
        this.createdTimeMs = createdTimeMs;
    }

    public void setContent(String content) {
        this.content = content;
        generateKey();
    }

    public boolean validate() {
        return content != null && key != null;
    }

    private void generateKey() {
        if (content != null) {
            key = DigestUtils.md5Hex(content);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (key != null ? !key.equals(entry.key) : entry.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
