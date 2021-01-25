package bankingapp.sessions;

import java.util.ArrayList;

import bankingapp.daos.AbstractDAO;
import bankingapp.daos.AccountsDAO;
import bankingapp.exceptions.BankException;
import bankingapp.utils.BankConsole;
import bankingapp.utils.PROMPTS;

public class CustomerSession extends UserSession{
	public CustomerSession(AbstractDAO account) {
		super();
		menu=PROMPTS.CUSTOMERMENU;
		user=account;
	}

	private void view() {
		ArrayList<AccountsDAO> accounts = user.getActiveAccounts();
		for(AccountsDAO account:accounts) {
			BankConsole.display(String.format(PROMPTS.ACCOUNT.toString(),account.getType(),account.getID()+"",account.getBalance()+""));
		}
	}
	private void deposit() {BankConsole.display("You have deposited money");}
	private void withdraw() {BankConsole.display("You have withdrawn money");}
	private void transfer() {BankConsole.display("This is a transfer");}
	private void viewPendingTransfers() {BankConsole.display("These are your transfers.");}
	private void acceptTransfer() {BankConsole.display("This is where you accept transfers");}
	@Override
	public boolean processInputs() throws BankException {
		switch(BankConsole.read().toLowerCase()) {
			case "1":
			case "apply":
			case "a": apply();
				break;
			case "2":
			case "view pending accounts":
			case "vpa": viewPendingAccounts();
				break;
			case "3":
			case "view":
			case "v": view();
				break;
			case "4":
			case "deposit":
			case "d": deposit();
				break;
			case "5":
			case "withdraw":
			case "w": withdraw();
				break;
			case "6":
			case "transfer":
			case "t": transfer();
				break;
			case "7":
			case "view pending transfers":
			case "vpt": viewPendingTransfers();
				break;
			case "8":
			case "accept":
			case "ac": acceptTransfer();
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
