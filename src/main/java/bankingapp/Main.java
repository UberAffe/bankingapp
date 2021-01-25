package bankingapp;

import bankingapp.sessions.SessionFactory;
import bankingapp.utils.BankConsole;

public class Main {
	
	public static void main(String[] args) {
		boolean keepgoing = true;
		SessionFactory.startUp();
		while(keepgoing) {
			keepgoing = SessionFactory.processInputs();
		}
		BankConsole.display("System Shutting Down");
	}
}
