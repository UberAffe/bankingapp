package bankingapp.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import bankingapp.pojos.SessionPOJO;

public class EmployeeAccount extends SessionPOJO implements SessionDAO {

	public EmployeeAccount(int userID, String un, String pw) {
		super(userID,un,pw);
	}

	@Override
	public void insert(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(String ...args) throws SQLException {
		return false;
	}

	@Override
	public void delete(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList select(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyForAccount(String string, double parseDouble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<AccountsDAO> getPendingAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountsDAO> getActiveAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
