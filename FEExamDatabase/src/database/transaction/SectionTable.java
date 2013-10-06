package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.config.Config;

public class SectionTable {

	public static boolean createSectionTable() {
		Connection c = null;
		PreparedStatement stmt = null;
		   try {
			   Class.forName(Config.SQLITE_JDBC);
			   c = DriverManager.getConnection(Config.CONNECTION_STRING);
			   
			   String sql = "CREATE TABLE " + Config.DATABASE_SECTION_TBL + "" +
			   		"(ID INTEGER PRIMARY KEY AUTOINCREMENT	NOT NULL,"
					   + " SECTIONNAME 		TEXT)";
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

	public static boolean addSectionEntity(String tblName) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "INSERT INTO " + Config.DATABASE_SECTION_TBL + " (SECTIONNAME) VALUES(?)";
			
			stmt = c.prepareStatement(sql);
			stmt.setString(1, tblName);
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
			}
		}

	}
	
	public static boolean removeSectionEntity(String tblName) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "DELETE FROM " + Config.DATABASE_SECTION_TBL + " WHERE SECTIONNAME=?";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, tblName);
			return (stmt.executeUpdate() > 0);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
			}
		}
	}
	
	public static List<String> selectAllSections() {
		List<String> res = new ArrayList<String>();
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "SELECT * FROM " + Config.DATABASE_SECTION_TBL;
			stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String section = rs.getString(2);
				res.add(section);
			}
			
			return res;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return res;
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
			}
		}
	}
}
