package bankingapp.sessions;

import bankingapp.daos.SessionDAO;
import bankingapp.daos.CustomerDAO;
import bankingapp.daos.DAOFactory;
import bankingapp.daos.EmployeeDAO;
import bankingapp.daos.UserDAO;
import bankingapp.exceptions.BankException;
import bankingapp.utils.BankConsole;
import bankingapp.utils.BankLog;

public class SessionFactory {
	private static Session user;

	public static boolean processInputs() {
		user.options();
		try {
			return user.processInputs();
		} catch (BankException e) {
			if (e.badlog) {
				BankConsole.display("Your login credentials are whack.");
			}
		}
		return false;
	}

	public static void login(String username, String password) throws BankException {
		SessionDAO account=DAOFactory.getSession(username, password);
		if(account instanceof CustomerDAO) {
			BankLog.info("A Customer has logged in as "+username+".");
			user= new CustomerSession(account);
		} else if(account instanceof EmployeeDAO) {
			BankLog.info("An Employee has logged in as "+username+".");
			user= new EmployeeSession(account);
		} else if(account instanceof UserDAO) {
			BankLog.info("An User has logged in as "+username+".");
			user= new UserSession(account);
		}
	}

	public static void register(String username, String password) {
		BankLog.info("new user has been registered");
	}

	public static void logout() {
		BankLog.info("User has logged out");
		user= new DefaultSession();
	}

	public static void startUp() {
		BankLog.info("Sessions starting up.");
		user = new DefaultSession();
	}
}
