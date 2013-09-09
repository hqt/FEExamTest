package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import database.config.Config;

public class SectionTable {

	public static boolean CreateSectionTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		System.out.println("Table created successfully");
		return true;
	}

	public static boolean AddSectionEntity(String name) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE " + "(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT    NOT NULL, "
					+ " AGE            INT     NOT NULL, "
					+ " ADDRESS        CHAR(50), " + " SALARY         REAL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		System.out.println("Table created successfully");
		return true;
	}

}
