package basic;

import database.CompaniesTable;

public class Company {
	
	private static int count = 1;
	private int _serialID;
	private String _name;
	private int _managerID;
	private int _adminID;
	private int[] _users;
	/**
	 * Sets a new company with the next available serial ID number and a general company name (new_0[COMPANYID]).
	 * Sets the current user as the manager user of the company.
	 * @param userID The managing user of the new company.
	 */
	public Company(int userID) {
		this._serialID = count;
		count = isIDAvailable(++count);
		this._name = "new_0" + this._serialID;
		this._managerID = userID;
		this._users = new int[1];
		this._users[0] = userID;
	}
	/**
	 * Sets a new company with the next available serial ID number and a the given company name.
	 * Sets the current user as the manager user of the company.
	 * @param userID The managing user of the new company.
	 * @param name The name for the new company.
	 */
	public Company(int userID, String name) {
		this._serialID = count;
		count++;
		this._name = name;
		this._managerID = userID;
		this._users = new int[1];
		this._users[0] = userID;
	}
	/**
	 * Sets a new company with the given ID number if available and a general company name (new_0[COMPANYID]).
	 * If the given id number is unavailable, sets the next available id number.
	 * Sets the current user as the manager user of the company.
	 * @param userID The managing user of the new company.
	 * @param id The id number of the new company.
	 */
	public Company(int userID, int id) {
		this._serialID = isIDAvailable(id);
		count = isIDAvailable(++id);
		this._name = "new_0" + this._serialID;
		this._managerID = userID;
		this._users = new int[1];
		this._users[0] = userID;
	}
	 
	private int isIDAvailable(int id) { //COMPLETE CHECK WITH DB
		return id;
	}
	
	/**
	 * Sets a new company with the given ID number if available and a the given company name.
	 * If the given id number is unavailable, sets the next available id number.
	 * Sets the current user as the manager user of the company.
	 * @param userID The managing user of the new company.
	 * @param name The name for the new company.
	 * @param id The id number of the new company.
	 */
	public Company(int userID, String name, int id) {
		this._serialID = isIDAvailable(id);
		count = isIDAvailable(++id);
		this._name = name;
		this._managerID = userID;
		this._users = new int[1];
		this._users[0] = userID;
	} 
	/**
	 * Copy constructor. Sets a new company with:
	 * The next available id number after the other company id number.
	 * [The name of the other company] + _copy.
	 * The same users as the other company and all other settings.
	 * @param other The company to copy.
	 * @param copyType insert 1 for copying accounts list, 2 for copying all company data including records and registries. insert 0 or any other integer to copy only the general company attributes and users.
	 */
	public Company (Company other, int copyType) {
		this._serialID = isIDAvailable(++other._serialID);
		count = isIDAvailable(++this._serialID);
		this._name = other._name + "_copy";
		this._managerID = other._managerID;
		this._users = other._users;
		if (copyType == 1) {
			System.out.println("copy accounts list");
		} else if (copyType == 2) {
			System.out.println("copy all company data including records and registries");
		}
		
	}
	
	public Company (Company other) {
		this._serialID = other._serialID;
		this._name = other._name;
		this._adminID = other._adminID;
		}
		
	public static String getCompanyName(int companyID) {
		return CompaniesTable.getCompanyName(companyID);
	}
	
	public static Company createNewCompany(int userid, int state, String companyName, String copiedCompanyName) {
		if (state == 1) {
			int companyID = CompaniesTable.createBlankCompany(userid, companyName);
			if (companyID != 0)
				return new Company (userid, companyName, companyID);
			else
				return null;
		} else if (state == 2) {
			CompaniesTable.createCompanyFromTemplate(userid, companyName, copiedCompanyName);
			return new Company(userid);
		} else {
			CompaniesTable.copyExistingCompany(userid, companyName, copiedCompanyName);
			return new Company(userid);
		}
	}
}
