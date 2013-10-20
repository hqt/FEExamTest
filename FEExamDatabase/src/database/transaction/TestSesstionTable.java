package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import database.config.Config;
import database.model.TestSession;

/**
 * table contains all questions in one phrase
 * @author Huynh Quang Thao
 *
 */
public class TestSesstionTable {
	
	public static boolean createTestSesstionTable() {
		Connection c = null;
		PreparedStatement stmt = null;
		   try {
			   Class.forName(Config.SQLITE_JDBC);
			   c = DriverManager.getConnection(Config.CONNECTION_STRING);
			   
			   String sql = "CREATE TABLE " + Config.DATABASE_TESTSESSION_TBL + "" +
			   		"(SESSIONID INTEGER PRIMARY KEY AUTOINCREMENT	NOT NULL,"
					    + " DATE 		TEXT,"
			   			+ "PROFILEID INT)";
			   
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
	
	public static boolean CreateTestSesstion(TestSession session) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "INSERT INTO " + Config.DATABASE_TESTSESSION_TBL + 
					" (DATE, PROFILEID) VALUES (?,?)";

			stmt = c.prepareStatement(sql);
			stmt.setString(1, session.getDoingDate().toString());
			stmt.setInt(2, session.getProfileId());
			
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
	
	public static List<TestSession> getAllTestSessionByProfile(int profileId) {
		List<TestSession> res = new ArrayList<TestSession>();
		Connection c = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "SELECT SESSIONID, DATE, PROFILEID "
					+ " FROM " + Config.DATABASE_TESTSESSION_TBL
					+ " WHERE " + Config.DATABASE_TESTSESSION_TBL+".PROFILEID=?";
			

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, profileId);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int SESSIONID = rs.getInt(0);
				String DATE = rs.getString(1);
				int PROFILEID = rs.getInt(3);
				TestSession session = new TestSession(SESSIONID, new SimpleDateFormat().parse(DATE), PROFILEID);
				res.add(session);
			}
			return res;
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return res;
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
