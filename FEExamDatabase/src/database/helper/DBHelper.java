package database.helper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.config.Config;

public class DBHelper {

	public static boolean isTableExists(String tableName) {
		if (tableName == null)
			return false;
		Connection c = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);
			DatabaseMetaData md = c.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				// TABLE_CAT String => table catalog (may be null)
				// TABLE_SCHEM String => table schema (may be null)
				// TABLE_NAME String => table name
			  if (rs.getString(3).equals(tableName)) return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
			}
		}
	}
	
}
