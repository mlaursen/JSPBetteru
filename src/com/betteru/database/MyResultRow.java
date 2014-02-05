package com.betteru.database;

import java.util.HashMap;
import java.util.Map;

public class MyResultRow {
	private Map<String, String> row;

	public MyResultRow() {
		this.row = new HashMap<String, String>();
	}

	public void add(String key, String value) {
		this.row.put(key, value);
	}

	public String get(String key) {
		return this.row.get(key);
	}

	public String toString() {
		return this.row.toString();
	}
}
