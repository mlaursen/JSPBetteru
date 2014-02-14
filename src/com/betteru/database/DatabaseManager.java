package com.betteru.database;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class DatabaseManager {


	public static Connection getConnection(String username, String password, String database) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection(database, username, password);
	}


	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String username = "jspbetteru";
		String password = "abcd1234";
		String database = "jdbc:oracle:thin:@localhost:1521:xe";
		return getConnection(username, password, database);
	}

	/**
	 * Executes a stored procedure and returns a boolean of success
	 * @param pname
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean executeStoredProcedure(String pname, Object... params) {
		boolean success = false;
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = getConnection();
			cs = conn.prepareCall("{call " + pname + "}");
			for(int i = 1; i <= params.length; i++) {
				Object p = params[i-1];
				applyDatatype(p, i, conn, cs);
				/*
				if(p instanceof Date) {
					cs.setDate(i, (Date) p);
				}
				else if(p instanceof Number) {
					cs.setDouble(i, (Double) p);
				}
				else if(p instanceof MyClob) {
					Clob c = conn.createClob();
					c.setString(1, ((MyClob) p).getValue());
					cs.setClob(i, c);
				}
				else {
					cs.setString(i, p.toString());
				}
				//cs.setString(i, params[i-1]);
				 */
			}
			success = cs.executeUpdate() > 0;
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(cs != null) {
				try {
					cs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	public static MyResultRow getStoredProcedureFirstRow(String pname, Object... params) {
		return getStoredProcedureCursor(pname, params).getRow();
	}

	/**
	 * Opens a connection to the database, creates a callable statement for the stored procedure,
	 * executes the stored procedure and returns the REFCURSOR result as a ResultSet.
	 * 
	 * @param pname		Procedure name. example: callstuff(?,?,?)
	 * @param params	Parameters to be passed to the procedure name. All excluding cursor.
	 * @return	A ResultSet of the out refcursor.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static MyResultSet getStoredProcedureCursor(String pname, Object... params) {
		int cursorPos = params.length+1;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		MyResultSet results = null;
		try {
			conn = getConnection();
			cs = conn.prepareCall("{call " + pname + "}");
			for(int i = 1; i <= params.length; i++) {
				//cs.setString(i, (String) params[i-1]);
				Object p = params[i-1];
				applyDatatype(p, i, conn, cs);
			}
			cs.registerOutParameter(cursorPos, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(cursorPos);
			results = MyResultSet.toMyResultSet(rs);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(cs != null) {
				try {
					cs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(rs != null) {
				try {
					rs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return results;
	}
	
	private static void applyDatatype(Object p, int i, Connection conn, CallableStatement cs) throws SQLException {
		if(p instanceof Date) {
			cs.setDate(i, (Date) p);
		}
		else if(p instanceof Integer || canParseInt(p)) {
			cs.setInt(i, Integer.parseInt((String) p));
		}
		else if(p instanceof Double) {
			cs.setDouble(i, (Double) p);
		}
		else if(p instanceof MyClob) {
			Clob c = conn.createClob();
			c.setString(1, ((MyClob) p).getValue());
			cs.setClob(i, c);
		}
		else {
			cs.setString(i, p.toString());
		}
	}
	
	private static boolean canParseInt(Object i) {
		try {
			Integer.parseInt((String)i);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}