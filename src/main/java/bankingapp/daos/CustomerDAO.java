package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bankingapp.utils.BankLog;

public class CustomerDAO extends UserDAO {
	
	private ArrayList<AccountsDAO> accounts = new ArrayList<AccountsDAO>();
	
	public CustomerDAO(int userID, String un, String pw) {
		super(userID, un,pw);
	}

	public ArrayList<AccountsDAO> getAccounts() {
		return accounts;
	}
	
	public void withdraw(int accountID, float amount) {
		
	}
	
	public void deposit(int accountID, float amount) {
		
	}
	
	public void postTransfer(int faid, int taid, float amount) {
		
	}
	
	public void acceptTransfer(int tid, boolean decision) {
		
	}
	
	public ArrayList getPendingTransfers() {
		return null;
	}
}
