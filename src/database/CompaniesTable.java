package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
