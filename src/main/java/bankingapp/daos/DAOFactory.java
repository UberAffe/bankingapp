package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankingapp.exceptions.EXCEPT;
import bankingapp.utils.BankLog;
import bankingapp.exceptions.BankException;

public class DAOFactory {

	public static SessionDAO getSession(String un, String pw) throws BankException {
		int userID = 0;
		String uType = "";
		try (Connection conn = Credentials.getConnection();) {
			PreparedStatement cs = conn.prepareStatement("select * from getuseridandtype(?,?)");
			cs.setString(1, un);
			cs.setString(2, pw);
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				userID = rs.getInt(1);
				uType = rs.getString(2);
				switch (uType) {
				case "CUSTOMER":
					return new CustomerDAO(userID, un, pw);
				case "EMPLOYEE":
					return new EmployeeDAO(userID, un, pw);
				default:
					return new UserDAO(userID, un, pw);
				}
			}
			throw new BankException(EXCEPT.LOGIN);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BankException(EXCEPT.CONNECTION);
		}

	}

	public static BasicDAO getUser(int userID, String username, String password, String userType) {
		BasicDAO user=null;
		switch(userType) {
			case "CUSTOMER":
				user=new CustomerDAO(userID, username, password);
				break;
			case "USER":
				user= new UserDAO(userID,username,password);
				break;
			case "EMPLOYEE":
				user=new EmployeeDAO(userID,username,password);
				break;
			default:
				BankLog.warn("The DAOFactory default for getUser shouldn't be reachable.");
		}
		return user;
	}
}
