package basic;

import database.UsersTable;

public class User {

	private int _userid;
	private String _username;
	private int[] _companiesIDs;
	
	public User() {
		
	}
	
	public User(int userid, String username, int[] companiesIDs) {
		_userid = userid;
		_username = username;
		_companiesIDs = companiesIDs;
	}
	
	public int getUserID() {
		return this._userid;
	}
	
	public void setUserID(int userid) {
		this._userid = userid;
	}
	
	public String getUsername() {
		return this._username;
	}
	
	public void setUsername(String username) {
		this._username = username;
	}
	
	public int[] getCompaniesIDs() {
		return this._companiesIDs;
	}
	
	public void setCompaniesIDs(int[] companiesIDs) {
		this._companiesIDs = companiesIDs;
	}
	
	public static boolean checkLogin(String username, String password) {
		return UsersTable.verifyUser(username, password);
	}
	
	public static boolean registerNewUser(String username, String password) {
		return  UsersTable.newUser(username, password);
	}
	

}
