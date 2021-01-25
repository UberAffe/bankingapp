package bankingapp.sessions;

import bankingapp.daos.AbstractDAO;
import bankingapp.utils.BankConsole;
import bankingapp.utils.PROMPTS;

public class EmployeeSession extends Session{

	public EmployeeSession(AbstractDAO account) {
		user=account;
		menu=PROMPTS.EMPLOYEEMENU;
	}

	@Override
	public boolean processInputs() {
		// TODO Auto-generated method stub
		return keepGoing;
	}

}
