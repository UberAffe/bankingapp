package bankingapp.sessions;

import java.util.ArrayList;

import bankingapp.daos.SessionDAO;
import bankingapp.daos.AccountsDAO;
import bankingapp.exceptions.BankException;
import bankingapp.utils.BankConsole;
import bankingapp.utils.PROMPTS;

public class UserSession extends Session{
	
	public UserSession() {}
	
	public UserSession(SessionDAO account) {
		user=account;
		menu=PROMPTS.USERMENU;
	}
	
	protected void apply() {
		String []inputs = new String[2];
		BankConsole.display(PROMPTS.APPLY);
		inputs[0]=BankConsole.read();
		BankConsole.display(PROMPTS.APPLY);
		inputs[1]=BankConsole.read();
		try{
			switch(inputs[0].toLowerCase()) {
				case "c":
				case "check":
				case "checking":
					user.applyForAccount("CHECKING", Math.floor(Double.parseDouble(inputs[1])*100)/100);
					break;
				case "s":
				case "save":
				case "saving":
				case "savings":
					user.applyForAccount("SAVING", Math.floor(Double.parseDouble(inputs[1])*100)/100);
					break;
				default:
					badInput();
					break;
			}
		}catch(NumberFormatException e) {
			BankConsole.display("When entering the amount it should be a number with no special characters or spaces.");
		}
	}
	
	protected void viewPendingAccounts() {
		ArrayList<AccountsDAO> accounts = user.getPendingAccounts();
		for(AccountsDAO account:accounts) {
			BankConsole.display(String.format(PROMPTS.PENDINGACCOUNT.toString(),account.getType(),account.getID()+""));
		}
	}

	@Override
	public boolean processInputs() throws BankException {
		String input = BankConsole.read();
		switch(input.toLowerCase()) {
			case "1":
			case "v":
			case "view":
				viewPendingAccounts();
				break;
			case "2":
			case "a":
			case "apply":
				apply();
				break;
			case "3":
			case "l":
			case "logout":
				logout();
				break;
			case "4":
			case "c":
			case "close":
				quit();
				break;
			default:
				badInput();
		}
		return keepGoing;
	}
}
