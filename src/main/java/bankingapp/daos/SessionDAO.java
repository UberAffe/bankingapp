package bankingapp.daos;

import java.util.ArrayList;

public interface SessionDAO extends BasicDAO{
	
	public void applyForAccount(String string, double parseDouble);
	public ArrayList<AccountsDAO> getPendingAccounts();
	
	public ArrayList<AccountsDAO> getActiveAccounts();
	
}
