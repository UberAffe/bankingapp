package bankingapp.sessions;

import bankingapp.daos.SessionDAO;
import bankingapp.exceptions.BankException;
import bankingapp.utils.BankConsole;
import bankingapp.utils.PROMPTS;

public abstract class Session {
	protected SessionDAO user;
	protected static boolean keepGoing=true;
	protected static boolean logout=false;
	protected PROMPTS menu;
	
	public void options() {menu.showMenu();}
	public abstract boolean processInputs() throws BankException;
	
	protected void badInput() {
		BankConsole.display("You have selected an invalid option, please try again.");
		}
	public boolean keepGoing() {return keepGoing;}
	public boolean isLoggingOut() {return logout;}
	protected final void logout() {SessionFactory.logout();}
	protected static void quit() {keepGoing=false;}
}
