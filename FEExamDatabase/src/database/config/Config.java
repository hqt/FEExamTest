package database.config;

public class Config {
	public static String DATABASE_FILE_NAME = "db.db";
	public static String DATABASE_NAME = "Database";
	public static String DATABASE_SECTION_TBL = "tblSection";
	public static String DATABASE_PROFILE_TBL = "fee_Profile_table";
	public static String DATABASE_TESTSESSION_TBL = "fee_TestSession";
	public static String DATABASE_SESSIONQUES_TBL = "fee_TestSessionQuestion";
	public static String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE_NAME;
	public static String SQLITE_JDBC = "org.sqlite.JDBC";
}
