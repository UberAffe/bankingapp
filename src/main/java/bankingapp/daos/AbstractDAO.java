package bankingapp.daos;

import java.util.ArrayList;

public class AbstractDAO extends Credentials{

	protected String username;
	protected String password;
	protected int userID;
	
	public AbstractDAO(int uid, String un, String pw) {
		userID=uid;
		username=un;
		password=pw;
	}
	public void applyForAccount(String string, double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<AccountsDAO> getPendingAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<AccountsDAO> getActiveAccounts(){
		return null;
	}
	
}
