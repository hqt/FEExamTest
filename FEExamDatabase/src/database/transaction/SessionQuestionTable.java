package database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.config.Config;
import database.model.SessionQuestion;

public class SessionQuestionTable {

	public static boolean createSesstionQuestionTable() {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "CREATE TABLE " + Config.DATABASE_SESSIONQUES_TBL + ""
					+ "(SESSIONQUESTIONID INTEGER PRIMARY KEY AUTOINCREMENT	NOT NULL,"
					+ " SESSIONID 		INT,"
					+ " CATEGORYNAME	TEXT,"
					+ " QUESTIONID		INT," 
					+ " PERCENTAGECORRECT	INT)";

			stmt = c.prepareStatement(sql);
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
			}
			catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public static boolean CreateNewSesstionQuestion(SessionQuestion question) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "INSERT INTO " + Config.DATABASE_SESSIONQUES_TBL
					+ " (SESSIONID, CATEGORYNAME, QUESTIONID, PERCENTAGECORRECT) VALUES(?,?,?,?)";

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, question.getSessionId());
			stmt.setString(2, question.getCategoryName());
			stmt.setInt(3, question.getIdQuestion());
			stmt.setInt(4, question.getPercentCorrect());

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
			}
			catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public static List<SessionQuestion> getAllSesstionQuestions(int sessionId) {
		List<SessionQuestion> res = new ArrayList<SessionQuestion>();
		Connection c = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(Config.SQLITE_JDBC);
			c = DriverManager.getConnection(Config.CONNECTION_STRING);

			String sql = "SELECT SESSIONQUESTIONID, SESSIONID, CATEGORYNAME, QUESTIONID, PERCENTAGECORRECT "
					+ " FROM " + Config.DATABASE_SESSIONQUES_TBL
					+ " WHERE " + Config.DATABASE_SESSIONQUES_TBL+".SESSIONID=?";
			

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, sessionId);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int sessionQuestionId = rs.getInt(0);
				int SessionId = rs.getInt(1);
				String categoryName = rs.getString(2);
				int questionId = rs.getInt(3);
				int percentCorrect = rs.getInt(4);
				SessionQuestion question = new SessionQuestion(sessionQuestionId, SessionId, categoryName, questionId, percentCorrect);
				res.add(question);
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
