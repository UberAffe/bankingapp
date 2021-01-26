package bankingapp.sessions;

import bankingapp.daos.SessionDAO;
import bankingapp.utils.BankConsole;
import bankingapp.utils.PROMPTS;

public class EmployeeSession extends Session{

	public EmployeeSession(SessionDAO account) {
		user=account;
		menu=PROMPTS.EMPLOYEEMENU;
	}

	@Override
	public boolean processInputs() {
		// TODO Auto-generated method stub
		return keepGoing;
	}

}
