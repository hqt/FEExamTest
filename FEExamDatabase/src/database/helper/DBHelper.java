package database.helper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
				if (rs.getString(3).equals(tableName))
					return true;
			}
			return false;
		}
		catch (Exception e) {
			return false;
		}
		finally {
			try {
				c.close();
			}
			catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	/**
	 * get the newest (and largest) of autoincrement index of table sqlite_sequence
	 *  reference: http://www.sqlite.org/autoinc.html
	 * http://www.sqlite.org/fileformat2.html#seqtab
	 */
	public static int getLastIndexOfTable(String tblName) {
		Connection c = null;
		PreparedStatement stmt = null;

		// magic table
		// see reference for more detail
		String system_table = "sqlite_sequence";
		
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "SELECT SEQ FROM " + system_table + " WHERE name=?";
			
			stmt = c.prepareStatement(sql);
			stmt.setString(1, tblName);
		
			ResultSet rs = stmt.executeQuery();
			return rs.getInt(1);
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return -1;
		}
		finally {
			try {
				stmt.close();
				c.close();
			}
			catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

}
