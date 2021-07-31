package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basic.Company;

public class CompaniesTable {

	public CompaniesTable() {
		// TODO Auto-generated constructor stub
	}

	public static String getCompanyName(int companyID) {
		String query = "SELECT companyname FROM companies WHERE companyid=?";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setInt(1, companyID);
			ResultSet rs = pst.executeQuery();
			String rsCompanyName = "";
			if (rs.next()) {
				rsCompanyName = rs.getString("companyname");
			}				
			return rsCompanyName;
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	public static int createBlankCompany(int userid, String companyName) {
		if (createBlankCompany(userid, companyName, true)) {
			String query = "SELECT companyid FROM companies WHERE companyname=?";
			String[] dbInfo  = DBInfo.getDBInfo();
			try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
					PreparedStatement pst = con.prepareStatement(query)){
				
				pst.setString(1, companyName);
				ResultSet rs = pst.executeQuery();
				int rsCompanyID = 0;
				if (rs.next()) {
					rsCompanyID = rs.getInt("companyid");
				}
				if (rsCompanyID != 0) {
					boolean success = createCompanyTables(rsCompanyID);
					if (success) {
						return rsCompanyID;
					}
				}
				return 0;
				
			}
			catch (SQLException ex) {
				ex.printStackTrace();
				return 0;
			}
		} else
			return 0;		
	}
	
	private static boolean createBlankCompany(int userid, String companyName, boolean override) {
		String query = "INSERT INTO companies (companyname, adminid) values (?,?)";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, companyName);
			pst.setInt(2, userid);
			int row = pst.executeUpdate();
			if (row > 0) {
				return true;
			}				
			return false;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}	
		
	}
	
	private static boolean createCompanyTables(int companyID) {
		String query = "CREATE TABLE groupcodes_? (code NUMERIC(5,0) PRIMARY KEY, name VARCHAR(20))";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, companyID);
			boolean success =  pst.execute();
			if (success) {
				return insertFirstGroupCode(companyID);
			} else {
				return false;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private static boolean insertFirstGroupCode(int companyID) {
		String query = "INSERT INTO groupcodes_? values (0,'empty')";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, companyID);
			int row = pst.executeUpdate();
			if (row > 0) {
				return createAccountsTable(companyID);
			} else {
				return false;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private static boolean createAccountsTable(int companyID) {
		String query = "CREATE TABLE accounts_? (accountid NUMERIC(9,0) PRIMARY KEY, name VARCHAR(20), groupcode NUMERIC (5,0) DEFAULT 0, FOREIGN KEY (groupcode) REFERENCES groupcodes_? (code)";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, companyID);
			pst.setInt(2, companyID);
			boolean success =  pst.execute();
			if (success) {
				return createRecordsTable(companyID);
			} else {
				return false;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private static boolean createRecordsTable(int companyID) {
		String query = "CREATE TABLE records_? (serial SERIAL PRIMARY KEY, recorddate DATE DEFAULT CURRENT_DATE, addingdate DATE DEFAULT CURRENT_DATE, valuedate DATE DEFAULT CURRENT_DATE, islocked BOOLEAN DEFAULT false, details VARCHAR(100)";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, companyID);
			boolean success =  pst.execute();
			if (success) {
				return createRegistriesTable(companyID);
			} else {
				return false;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private static boolean createRegistriesTable(int companyID) {
		String query = "CREATE TABLE registries_? (serial SERIAL PRIMARY KEY, recordid INT NOT NULL, accountid NUMERIC(9,0) NOT NULL, amount DECIMAL(20,3) DEFAULT 0, isdebit BOOLEAN DEFAULT true, FOREIGN KEY (recordid) REFERENCES records_? (serial), FOREIGN KEY (accountid) REFERENCES accounts_? (accountid)";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, companyID);
			pst.setInt(2, companyID);
			pst.setInt(3, companyID);
			return pst.execute();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static boolean createCompanyFromTemplate(int userid, String companyName, String copiedCompanyName) {
		String query = "INSERT INTO companies (companyname, adminid) values (?,?)";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, companyName);
			pst.setInt(2, userid);
			int row = pst.executeUpdate();
			if (row > 0) {
				return true;
			}				
			return false;
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}			
	}

	public static boolean copyExistingCompany(int userid, String companyName, String copiedCompanyName) {
		String query = "INSERT INTO companies (companyname, adminid) values (?,?)";
		String[] dbInfo  = DBInfo.getDBInfo();
		try(Connection con = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, companyName);
			pst.setInt(2, userid);
			int row = pst.executeUpdate();
			if (row > 0) {
				return true;
			}				
			return false;
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}			
	}
}
