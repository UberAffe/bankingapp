package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bankingapp.exceptions.BankException;
import bankingapp.exceptions.EXCEPT;
import bankingapp.pojos.SessionPOJO;
import bankingapp.utils.BankLog;

public class UserDAO extends SessionPOJO implements SessionDAO{
	
	public UserDAO(int userID, String un, String pw) {
		super(userID, un, pw);
	}

	public static void registerUser(String un, String pw) throws BankException {
		try(Connection conn = Credentials.getConnection();){
			PreparedStatement ps = conn.prepareStatement("select * from registeruser(?,?,?)");
			ps.setString(1, un);
			ps.setString(2, pw);
			ps.setString(3,"CUSTOMER");
			ResultSet rs=ps.executeQuery();
			rs.next();
			if(!rs.getBoolean(1)) {
				throw new BankException(EXCEPT.REGISTER);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankException(EXCEPT.REGISTER);
			//e.printStackTrace();
		} 
	}
	
	public void applyForAccount(String type, float amount) {
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs = conn.prepareCall("call registeraccount(?, ?, ?)");
			cs.setInt(1, userID);
			cs.setFloat(2, amount);
			cs.setString(3, type);
			if(!cs.execute()) {
				BankLog.warn("Account was not created for authorization.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			BankLog.warn(e.getMessage());
		}
	}
	
	@Override
	public ArrayList<AccountsDAO> getPendingAccounts() {
		return AccountsDAO.getAccounts(userID, false);
	}
	
	@Override
	public ArrayList<AccountsDAO> getActiveAccounts(){
		return AccountsDAO.getAccounts(userID, true);
	}

	@Override
	public void insert(String... args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(String... args) throws SQLException {
		return false;
		
	}

	@Override
	public void delete(String... args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList select(String... args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
