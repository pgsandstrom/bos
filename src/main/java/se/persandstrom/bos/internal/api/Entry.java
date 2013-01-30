package se.persandstrom.bos.internal.api;

import org.apache.commons.codec.digest.DigestUtils;

public class Entry {

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

}
