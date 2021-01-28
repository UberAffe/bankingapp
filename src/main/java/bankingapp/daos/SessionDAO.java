package bankingapp.daos;

import java.util.ArrayList;

public interface SessionDAO extends BasicDAO{
	
	public void applyForAccount(String string, float parseDouble);
	public ArrayList<AccountsDAO> getPendingAccounts();
	
	public ArrayList<AccountsDAO> getActiveAccounts();
	
}
