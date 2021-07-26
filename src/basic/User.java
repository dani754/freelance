/**
 * User class
 */
package basic;

import java.sql.SQLException;

import database.UsersTable;

public class User {

	private int _userid;
	private String _username;
	private int[] _companiesIDs;
	
	/**
	 * A constructor for a new user.
	 * @param userid
	 * @param username
	 * @param companiesIDs
	 */
	public User(int userid, String username, int[] companiesIDs) {
		_userid = userid;
		_username = username;
		_companiesIDs = companiesIDs;
	}
	
	public User(User other) {
		this._userid = other._userid;
		this._username = other._username;
		this._companiesIDs = other._companiesIDs;
	}
	/**
	 * Get user ID.
	 * @return
	 */
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
	
	public static User getByName(String username) throws SQLException {
		return UsersTable.getByName(username);
	}
	

}
