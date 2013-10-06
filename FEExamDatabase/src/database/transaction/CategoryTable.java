package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public static List<Question> GetAllEntities(String tblName) {
		Connection c = null;
		PreparedStatement stmt = null;
		List<Question> res = new ArrayList<Question>();
		
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);
			String sql = "SELECT * FROM " + tblName;
			stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String question = rs.getString(1);
				String ansa = rs.getString(2);
				String ansb = rs.getString(3);
				String ansc = rs.getString(4);
				String ansd = rs.getString(5);
				String anse = rs.getString(6);
				String exp = rs.getString(7);
				int result = rs.getInt(8);
				byte[] img = rs.getBytes(9);
				byte[] imga = rs.getBytes(10);
				byte[] imgb = rs.getBytes(11);
				byte[] imgc = rs.getBytes(12);
				byte[] imgd = rs.getBytes(13);
				byte[] imge = rs.getBytes(14);
				byte[] imgexp = rs.getBytes(15);
				Question q = new Question(question, ansa, ansb, ansc, ansd, anse,
						img, imga, imgb, imgc, imgd, imge, exp, imgexp, result, tblName);
				res.add(q);
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
			stmt.setBytes(9, question.getImg());
			stmt.setBytes(10, question.getImga());
			stmt.setBytes(11, question.getImgb());
			stmt.setBytes(12, question.getImgc());
			stmt.setBytes(13, question.getImgd());
			stmt.setBytes(14, question.getImge());
			stmt.setBytes(15, question.getImgexp());

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
