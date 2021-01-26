package bankingapp.utils;

public enum PROMPTS {
	LOGIN("Please type your username and hit enter.","Please type your password and hit enter."),
	FAILEDLOGIN("Your username and password do not match any registered users."),
	APPLY("If you want to apply for a checking account type 'checking', if you want to apply\n"
			+"for a savings account type 'saving'.",
			"Enter the amount of money you are initially depositing to open this account."),
	PENDINGACCOUNT("Your {0} account with account number {1} is still pending approval."),
	ACCOUNT("Your {0} account with account number {1}, currently has a balance of {3}"),
	REGISTER("Please enter your desired username.",
			"Please enter your desired password."),
	SUCCESSREGISTER("Your account has been registered with username {0},\nand is waiting for approval."),
	FAILEDREGISTER("Username {0} already exists,\nplease choose a different username."),
	STARTUPMENU("1. 'register' for a new account (r)",
			"2. 'login' to an existing account (l)"),
	USERMENU("1. 'view' pending accounts (v)",
			"2. 'apply' for new account (a)",
			"3. 'logout' of account (l)",
			"4. 'close' application (c)"),
	CUSTOMERMENU("1. 'apply' for a new account (a)",
				"2. 'view pending accounts' (vpa)",
				"3. 'view' active accounts (v)",
				"4. 'deposit' money into an account (d)",
				"5. 'withdraw' money from account (w)",
				"6. 'transfer' money to another account (t)",
				"7. 'view pending transfers' (vpt)",
				"8. 'accept' money transfer (a)",
				"9. 'logout' (l)",
				"10. 'close' application (c)"),
	EMPLOYEEMENU("1. view pending");
	private String []text;
	private String instructions;
	private int position;
	PROMPTS(String ...text){
		this.text = text;
		instructions = "To choose an option, you can type the number, 'phrase', or (letter code).";
		position=0;
	}
	@Override
	public String toString() {return getLocalizedString();}
	private String getLocalizedString() {
		String output = text[position];
		position+=1;
		position%=text.length;
		return output;
	}
	public static void reset(PROMPTS p) {p.position=0;}
	public void showMenu() {
		BankConsole.display(instructions);
		for (String s:text) {
			BankConsole.display(s);
		}
	}
}
