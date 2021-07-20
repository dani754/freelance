package basic;

import database.UsersTable;

public class User {

	private int _userid;
	private String _username;
	private int[] _companiesIDs;
	
	public User(int userid, String username, int[] companiesIDs) {
		_userid = userid;
		_username = username;
		_companiesIDs = companiesIDs;
	}
	
	public static boolean checkLogin(String username, String password) {
		return UsersTable.verifyUser(username, password);
	}
	

}
