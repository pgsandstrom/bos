package se.persandstrom.bos.internal.api;

public class Entry {

	public final long createdTimeMs;
	public final String content;

	public Entry(long createdTimeMs, String content) {
		this.createdTimeMs = createdTimeMs;
		this.content = content;
	}

	public long getCreatedTimeMs() {
		return createdTimeMs;
	}

	public String getContent() {
		return content;
	}

}
