package database;

import java.sql.*;

public class DBInfo {
	
	private static String url = "jdbc:postgresql://localhost:5432/freelance";
	private static String dbUser = "postgres";
	private static String dbPass = "admin";

	public DBInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public static String[] getDBInfo() {
		String[] info = {url,dbUser,dbPass};
		return info;
	}
}
