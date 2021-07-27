package database;

import java.sql.*;

import basic.User;

public class UsersTable {

	public UsersTable() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean checkLogin(String username, String password) {
		String query = "SELECT * FROM users WHERE username=?";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			String rsUsername = "";
			String rsPass = "";
			if (rs.next()) {
				rsUsername = rs.getString("username");
				rsPass = rs.getString("upassword");
			}
			if (rsUsername.equals(username) && rsPass.equals(password)) {
				
				return true;
			}
			else
				return false;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}		
	}
	
	public static boolean newUser(String username, String password) {
		if (isOriginalUsername(username))
			return addNewUser(username,password);
		else
			return false;
	}
	
	private static boolean isOriginalUsername(String username) {
		String query = "SELECT * FROM users WHERE username=?";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				return false;
			else 
				return true;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}		
	}
	
	private static boolean addNewUser(String username, String password) {
		String query = "INSERT INTO users (username,upassword) VALUES (?,?)";
		String[] dbInfo = DBInfo.getDBInfo();
		try (Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setString(1,  username);
			pst.setString(2, password);
			int updateCount = pst.executeUpdate();
			if (updateCount > 0)
				return true;
			else
				return false;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}	
	}
	
	public static User getByName(String username) throws  SQLException {
		String query = "SELECT * FROM users WHERE username=?";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			int rsUserid = 0;
			String rsUsername = "";
			Array rsCompaniesArray;
			if (rs.next()) {
				rsUserid = rs.getInt("userid");
				rsUsername = rs.getString("username");
				rsCompaniesArray = rs.getArray("companies");
				Integer[] arr = (Integer[])rsCompaniesArray.getArray();
				int[] rsCompaniesIDs= new int[arr.length];
				for (int i=0; i<arr.length; i++) {
					rsCompaniesIDs[i] = arr[i].intValue();
				}
				return new User(rsUserid,rsUsername,rsCompaniesIDs);
			}			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;		
	}
	

}
