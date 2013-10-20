package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.config.Config;
import database.model.Profile;

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
	
	public static Profile getProfileByName(String name) {
		Connection c = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);
			String sql = "SELECT * FROM " + Config.DATABASE_PROFILE_TBL
					+ " WHERE PROFILENAME=?";
			
			stmt = c.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String profileName = rs.getString(2);
				return new Profile(id, profileName);
			}
			return null;
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
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
