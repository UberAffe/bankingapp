package bankingapp.utils;

public enum PROMPTS {
	LOGIN("Please type your username and hit enter.","Please type your password and hit enter."),
	FAILEDLOGIN("Your username and password do not match any registered users."),
	APPLY("If you want to apply for a checking account type 'checking', if you want to apply\n"
			+"for a savings account type 'saving'.",
			"Enter the amount of money you are initially depositing to open this account."),
	PENDINGACCOUNT("Your %s account with account number %s is still pending approval."),
	ACCOUNT("|Account Type|Account # |    Balance    |",
			"|%-12s|%10s|$%-14s|"),
	REGISTER("Please enter your desired username.",
			"Please enter your desired password."),
	SUCCESSREGISTER("Your account has been registered with username %s,\nand is waiting for approval."),
	FAILEDREGISTER("Username %s already exists,\nplease choose a different username."),
	STARTUPMENU("1. 'register' for a new account (r)",
			"2. 'login' to an existing account (l)",
			"3. 'close' the application (c)"),
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
				"8. 'accept' money transfer (amt)",
				"9. 'logout' (l)",
				"10. 'close' application (c)"),
	EMPLOYEEMENU("1. 'view pending users' (vpu)",
				"2. 'view pending accounts' (vpa)",
				"3. 'approve user' (au)",
				"4. 'approve account' (aa)",
				"5. 'view a' customer's accounts (vca)",
				"6. 'view all' transactions (vt)",
				"7. 'logout' (l)",
				"8. 'close' application (c)"), 
	DEPOSIT("Please enter the number of the account you wish to deposit into.",
			"Please enter the amount you would like to deposit."), 
	WITHDRAW("Please enter the number of the account you wish to withdraw from.",
			"Please enter the amount you would like to withdraw."),
	TRANSFER("Please enter the number of the account you want to transfer from.",
			"Please enter the amount you would like to transfer.",
			"Please enter the number of the account you want to transfer to."),
	PENDINGTRANSFER("These are transfers to your account.\n"
			+"|Transfer #  |From Account|From User |    Amount    | To Account |",
			"|%-12s|%-12s|%-10s|$%-13s|%12s|",
			"These are transfers from your account.\n"
			+"|Transfer #  |From Account|    Amount    | To Account |",
			"|%-12s|%-12s|$%-13s|%12s|"),
	ACCEPTTRANSFER("Enter the transaction # that you are making a decision on.",
			"Enter your decision as 'approve'(a) or 'deny'(d)"), 
	PENDINGUSER("User with id %s is waiting for approval.");
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
	public String get(int i) {return text[i];}
	public void showMenu() {
		BankConsole.display(instructions);
		for (String s:text) {
			BankConsole.display(s);
		}
	}
	
}
