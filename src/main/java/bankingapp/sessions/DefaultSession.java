package bankingapp.sessions;

import java.util.Arrays;

import bankingapp.daos.UserAccount;
import bankingapp.exceptions.BankException;
import bankingapp.utils.BankConsole;
import bankingapp.utils.BankLog;
import bankingapp.utils.PROMPTS;

public class DefaultSession extends Session{

	public DefaultSession() {
		menu = PROMPTS.STARTUP;
	}
	@Override
	public boolean processInputs() throws BankException {
		String input=BankConsole.read();
		BankConsole.display("This is what you did as a default user");
		switch(input.toLowerCase()) {
			case "r":
			case "register":
				registerUser();
				break;
			case "l":
			case "login":
				login();
				break;
			case "q":
				quit();
				break;
		}
		return keepGoing;
	}

	private void login() {
		String []inputs= new String[2];
		BankConsole.display(PROMPTS.LOGIN);
		inputs[0]=BankConsole.read();
		BankConsole.display(PROMPTS.LOGIN);
		inputs[1]=BankConsole.read();
		if(inputs.length==2) {
			BankLog.info("Attempting log in with: "+Arrays.toString(inputs));
			try {
				SessionFactory.login(inputs[0],inputs[1]);
			} catch (BankException e) {
				switch(e.reason) {
					case CONNECTION:
						BankConsole.display("Connection to the server is bad, please try again later.");
						break;
					case LOGIN:
						BankConsole.display("Your login credentials do not match any users.");
				default:
					break;
				}
			}
		} else {
			badInput();
		}
	}

	private void registerUser() {
		BankConsole.display(PROMPTS.REGISTER.toString());
		String un = BankConsole.read();
		String ps = BankConsole.read();
		try {
			UserAccount.registerUser(un, ps);
			BankConsole.display("User "+un+" has been registered and is pending approval.");
		} catch (BankException e) {
			switch(e.reason) {
				case REGISTER:
					BankConsole.display("Username "+un+" already exists,\nplease choose a different username.");
					BankLog.warn("Username "+un+" already exists.");
					break;
			default:
				break;
			}
		}
	}

}
