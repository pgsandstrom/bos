package se.persandstrom.bos.internal.api;

import org.apache.commons.codec.digest.DigestUtils;

public class Entry {

	private long createdTimeMs;
	private String content;

	private String key;

	public Entry() {
		this.createdTimeMs = 0;
		this.content = null;
	}

	public Entry(long createdTimeMs, String content) {
		this.createdTimeMs = createdTimeMs;
		this.content = content;
		generateKey();
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
