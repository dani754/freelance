package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTable {

	public UsersTable() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean verifyUser(String username, String password) {
		String query = "SELECT * FROM users WHERE username=?";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			String rsUsername = "";
			String rsPass = "";
			while (rs.next()) {
				rsUsername = rs.getString("username");
				rsPass = rs.getString("password");
			}
			if (rsUsername.equals(username) && rsPass.equals(password))
				return true;
			else
				return false;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}		
	}
	

}
