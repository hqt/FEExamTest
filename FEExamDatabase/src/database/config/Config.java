package database.config;

public class Config {
	public static String DATABASE_FILE_NAME = "db.db";
	public static String DATABASE_NAME = "Database";
	public static String DATABASE_SECTION_TBL = "tblSection";
	public static String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE_NAME;
	public static String SQLITE_JDBC = "org.sqlite.JDBC";
}
