package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import database.config.Config;
import database.model.Question;

public class CategoryTable {

	public static boolean CreateNewTable(String tblName) {
	   Connection c = null;
	   PreparedStatement stmt = null;
	   try {
		   Class.forName(Config.SQLITE_JDBC);
		   c = DriverManager.getConnection(Config.CONNECTION_STRING);
		   
		   String sql = "CREATE TABLE " + tblName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT	NOT NULL,"
				   + " QUESTION 		TEXT,"
				   + " ANSA				TEXT," 
				   + " ANSB				TEXT," 
				   + " ANSC				TEXT," 
				   + " ANSD				TEXT," 
				   + " ANSE				TEXT," 
				   + " EXP				TEXT,"
				   + " RES			    INT," 
				   + " IMG 				BLOB," 
				   + " IMGA				BLOB,"
				   + " IMGB				BLOB,"
				   + " IMGC				BLOB,"
				   + " IMGD				BLOB,"
				   + " IMGE				BLOB,"
				   + " IMGEXP			BLOB)";
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

	public static boolean AddEntity(String tblName, Question question) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "INSERT INTO " + tblName + " (QUESTION, " +
					"ANSA, ANSB, ANSC, ANSD, ANSE, EXP, RES, " +
					"IMG, IMGA, IMGB, IMGC, IMGD, IMGE, IMGEXP)  VALUES(" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	
			
			System.out.println("prepare for excuted");
			System.out.println(sql);
			if (c == null) System.out.println("shit");
			stmt = c.prepareStatement(sql);
			if (stmt == null) System.out.println("fuck");
			stmt.setString(1, question.getQuestion());
			stmt.setString(2, question.getAnsa());
			stmt.setString(3, question.getAnsb());
			stmt.setString(4, question.getAnsc());
			stmt.setString(5, question.getAnsd());
			stmt.setString(6, question.getAnse());
			stmt.setString(7, question.getExplanation());
			stmt.setInt(8, question.getAns());
			stmt.setBlob(9, new SerialBlob(question.getImg()));
			stmt.setBlob(10, new SerialBlob(question.getImga()));
			stmt.setBlob(11, new SerialBlob(question.getImgb()));
			stmt.setBlob(12, new SerialBlob(question.getImgc()));
			stmt.setBlob(13, new SerialBlob(question.getImgd()));
			stmt.setBlob(14, new SerialBlob(question.getImge()));
			stmt.setBlob(15, new SerialBlob(question.getImgexp()));

			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
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

	public static boolean RemoveTable(String tblName) {
		Connection c = null;
		   PreparedStatement stmt = null;
		   try {
			   Class.forName(Config.SQLITE_JDBC);
			   c = DriverManager.getConnection(Config.CONNECTION_STRING);
			   
			   String sql = "DROP TABLE IF EXISTS " + tblName;
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
