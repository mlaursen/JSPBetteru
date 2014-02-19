package com_old.betteru.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyResultSet implements Iterable<MyResultRow> {
	private List<MyResultRow> rs;
	private List<String> colNames;
	private int size;
	public MyResultSet(List<MyResultRow> rs) {
		this.rs = rs;
		this.size = rs.size();
	}

	public MyResultSet(List<MyResultRow> rs, List<String> colNames) {
		this(rs);
		this.colNames = colNames;
	}

	public String getColumn(int rowNum, String colName) {
		return this.getRow(rowNum).get(colName);
	}

	public MyResultRow getRow() {
		return this.getRow(0);
	}


	public MyResultRow getRow(int rowNum) {
		if (size == 0 || rowNum > size)
			return null;
		else
			return this.rs.get(rowNum);
	}

	public static MyResultSet toMyResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		List<String> colNames = new ArrayList<String>();
		for (int i = 1; i <= cols; i++)
			colNames.add(rsmd.getColumnName(i).toLowerCase());
		
		
		List<MyResultRow> rows = new ArrayList<MyResultRow>();
		while (rs.next()) {
			MyResultRow columns = new MyResultRow();
			for (int i = 1; i <= cols; i++) {
				columns.add(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
			}
			rows.add(columns);
		}
		return new MyResultSet(rows, colNames);
	}

	public List<String> getColNames() {
		return colNames;
	}

	@Override
	public Iterator<MyResultRow> iterator() {
		return this.rs.iterator();
	}

	
	/**
	 * Turns the result set into a List of Class type.
	 * 
	 * The generic type T *MUST* have a constructor that takes a MyResultRow only.
	 * 
	 * @param type	The class to cast the list to
	 * @return	List of Class type
	 */
	public <T extends DatabaseObject> List<T> toListOf(Class<T> type) {
		List<T> list = new ArrayList<>();
		try {
			for (MyResultRow r : rs) {
				list.add(r.construct(type));
			}
		}
		catch (InstantiationException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyResultSet [rs=" + rs + ", colNames=" + colNames + ", size=" + size + "]";
	}
}
