package database;

import java.sql.*;

public class PostgreSqlDB {
	
	private static String url = "jdbc:postgresql://localhost:5432/freelance";
	private static String dbUser = "postgres";
	private static String dbPass = "admin";

	public PostgreSqlDB() {
		// TODO Auto-generated constructor stub
	}
	
	public static String[] dbInfo() {
		String[] info = {url,dbUser,dbPass};
		return info;
	}
}
