package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import bankingapp.exceptions.EXCEPT;
import bankingapp.exceptions.BankException;

public class DAOFactory extends Credentials{
	
	public static AbstractDAO getAccount(String un, String pw) throws BankException {
		int userID=0;
		String uType="";
		try(Connection conn = getConnection();){
			CallableStatement []calls = {
					conn.prepareCall("call getuserid(?,?,?,?)"),
					conn.prepareCall("call getuseraccounts(?)")};
			calls[0].setString(1, un);
			calls[0].setString(2, pw);
			calls[0].setString(3, uType);
			calls[0].setInt(4, userID);
			if(calls[0].execute()) {
				switch(uType) {
					case "Customer":
						return new CustomerAccount(userID,un,pw);
					case "Employee":
						return new EmployeeAccount(userID,un,pw);
					default:
						return new UserAccount(userID,un,pw);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankException(EXCEPT.CONNECTION);
		}
		throw new BankException(EXCEPT.LOGIN);
	}
}
