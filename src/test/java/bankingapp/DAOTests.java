package bankingapp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bankingapp.daos.Credentials;
import bankingapp.daos.UserDAO;
import bankingapp.exceptions.BankException;
import bankingapp.exceptions.EXCEPT;

public class DAOTests {
	
	@Test
	public void testRegisterUser() {
		try {
			UserDAO.registerUser("matt", "keran");
		} catch (BankException e) {
			Assertions.assertTrue(e.reason==EXCEPT.LOGIN);
		}
	}
	
	@Test
	public void testGetUserIDAndType() {
		try(Connection conn = Credentials.getConnection();){
			PreparedStatement cs = conn.prepareStatement("select * from getuseridandtype(?,?)");
			cs.setString(1,"matt");
			cs.setString(2, "keran");
			ResultSet rs=cs.executeQuery();
			Assertions.assertTrue(rs.next());
			Assertions.assertTrue(rs.getInt(1)==3);
			Assertions.assertTrue(rs.getString(2).equals("CUSTOMER"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
