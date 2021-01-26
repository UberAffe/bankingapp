package bankingapp.daos;

import java.util.ArrayList;

public class SessionDAO extends Credentials implements BasicDAO{

	protected String username;
	protected String password;
	protected int userID;
	
	public SessionDAO(int uid, String un, String pw) {
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
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}
	
}
