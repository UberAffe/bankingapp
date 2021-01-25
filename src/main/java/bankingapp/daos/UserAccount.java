package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bankingapp.exceptions.BankException;
import bankingapp.exceptions.EXCEPT;
import bankingapp.utils.BankLog;

public class UserAccount extends AbstractDAO{
	
	public UserAccount(int uid, String un, String pw) {
		super(uid, un, pw);
		// TODO Auto-generated constructor stub
	}

	public static void registerUser(String un, String pw) throws BankException {
		try(Connection conn = getConnection();){
			CallableStatement cs = conn.prepareCall("{?= call registeruser(?,?,?)}");
			cs.registerOutParameter(1, Types.BOOLEAN);
			cs.setString(2, un);
			cs.setString(3, pw);
			cs.setString(4,"CUSTOMER");
			if(cs.execute()) {
				if(!cs.getBoolean(1)) {
					throw new BankException(EXCEPT.REGISTER);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void applyForAccount(String type, double amount) {
		try(Connection conn = getConnection();){
			CallableStatement cs = conn.prepareCall("?= registeraccount(?, ?, ?)");
			cs.registerOutParameter(1, Types.BOOLEAN);
			cs.setString(2, username);
			cs.setDouble(3, amount);
			cs.setString(4, type);
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
}
