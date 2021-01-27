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
		switch(BankConsole.read().toLowerCase()) {
		case "1":
		case "":
		case "": 
			break;
		case "2":
		case "":
		case "": 
			break;
		case "3":
		case "":
		case "": 
			break;
		case "4":
		case "":
		case "": 
			break;
		case "5":
		case "":
		case "":
			break;
		case "6":
		case "":
		case "":
			break;
		case "7":
		case "":
		case "": 
			break;
		case "8":
		case "":
		case "": 
			break;
		case "9":
		case "logout":
		case "l": logout();
			break;
		case "10":
		case "close":
		case "c": quit();
			break;
		default: badInput();
			break;
	}
	return keepGoing;
	}

}
