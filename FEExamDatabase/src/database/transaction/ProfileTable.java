package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.config.Config;

public class ProfileTable {

	public static boolean CreateNewProfile(String name) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "INSERT INTO " + Config.DATABASE_PROFILE_TBL + " (PROFILENAME) VALUES(?)";

			stmt = c.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		finally {
	    	try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
	    }
		
	}
	
	public static boolean createProfileTable() {
		Connection c = null;
		PreparedStatement stmt = null;
		   try {
			   Class.forName(Config.SQLITE_JDBC);
			   c = DriverManager.getConnection(Config.CONNECTION_STRING);
			   
			   String sql = "CREATE TABLE " + Config.DATABASE_PROFILE_TBL + "" +
			   		"(ID INTEGER PRIMARY KEY AUTOINCREMENT	NOT NULL,"
					   + " PROFILENAME 		TEXT)";
			   stmt = c.prepareStatement(sql);
			   stmt.executeUpdate();
			   return true;
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				return false;
			}
		   finally {
		    	try {
					stmt.close();
					c.close();
				} catch (SQLException e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
		    }
	}
}
