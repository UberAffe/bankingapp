package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankingapp.exceptions.EXCEPT;
import bankingapp.exceptions.BankException;

public class DAOFactory extends Credentials {

	public static SessionDAO getAccount(String un, String pw) throws BankException {
		int userID = 0;
		String uType = "";
		try (Connection conn = getConnection();) {
			CallableStatement cs = conn.prepareCall("call getuseridandtype(?,?)");
			cs.setString(1, un);
			cs.setString(2, pw);
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				userID = rs.getInt(1);
				uType = rs.getString(2);
				switch (uType) {
				case "Customer":
					return new CustomerAccount(userID, un, pw);
				case "Employee":
					return new EmployeeAccount(userID, un, pw);
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
}
