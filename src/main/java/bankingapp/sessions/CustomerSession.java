package bankingapp.sessions;

import java.sql.SQLException;
import java.util.ArrayList;

import bankingapp.daos.SessionDAO;
import bankingapp.daos.TransferDAO;
import bankingapp.daos.AccountsDAO;
import bankingapp.exceptions.BankException;
import bankingapp.pojos.SessionPOJO;
import bankingapp.pojos.TransferPOJO;
import bankingapp.utils.BankConsole;
import bankingapp.utils.PROMPTS;

public class CustomerSession extends UserSession{
	public CustomerSession(SessionDAO userAccount) {
		super();
		menu=PROMPTS.CUSTOMERMENU;
		user=userAccount;
	}

	private void view() {
		ArrayList<AccountsDAO> accounts = user.getActiveAccounts();
		BankConsole.display(PROMPTS.ACCOUNT.get(0));
		for(AccountsDAO account:accounts) {
			BankConsole.display(String.format(PROMPTS.ACCOUNT.get(1),account.getType(),account.getID()+"",(float)(Math.floor(account.getBalance()*100)/100)+""));
		}
	}
	private void deposit() {
		ArrayList<AccountsDAO> accounts = user.getActiveAccounts();
		SessionPOJO userInfo = (SessionPOJO) user;
		BankConsole.display(PROMPTS.DEPOSIT);
		int ac = BankConsole.readI();
		BankConsole.display(PROMPTS.DEPOSIT);
		float am = BankConsole.readF();
		if(am>0) {
			for(AccountsDAO account:accounts) {
				if(account.getId()==ac) {
					try {
						account.update(userInfo.getUserID()+"",am+"","deposit");
						BankConsole.display("Successfully deposited $"+am);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			badInput();
		}
	}
	private void withdraw() {
		ArrayList<AccountsDAO> accounts = user.getActiveAccounts();
		SessionPOJO userInfo = (SessionPOJO) user;
		BankConsole.display(PROMPTS.WITHDRAW);
		int ac = BankConsole.readI();
		BankConsole.display(PROMPTS.WITHDRAW);
		float am = BankConsole.readF();
		if(am>0) {
			for(AccountsDAO account:accounts) {
				if(account.getID()==ac) {
					try {
						account.update(userInfo.getUserID()+"",am+"","withdraw");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	private void transfer() {
		TransferDAO transfer;
		SessionPOJO userInfo = (SessionPOJO) user;
		BankConsole.display(PROMPTS.TRANSFER);
		int fac = BankConsole.readI();
		BankConsole.display(PROMPTS.TRANSFER);
		float amount = BankConsole.readF();
		BankConsole.display(PROMPTS.TRANSFER);
		int tac = BankConsole.readI();
		try {
			transfer = new TransferDAO(userInfo.getUserID(),fac,amount,tac);
			transfer.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void viewPendingTransfers() {
		SessionPOJO userInfo = (SessionPOJO) user;
		TransferDAO td = new TransferDAO();
		try {
			ArrayList<TransferPOJO> ts = td.select(userInfo.getUserID()+"");
			BankConsole.display(PROMPTS.PENDINGTRANSFER.get(0));
			boolean flag= false;
			for(TransferPOJO t:ts) {
				if(!flag && t.getFromuid()==userInfo.getUserID()) {
					flag=true;
					BankConsole.display(PROMPTS.PENDINGTRANSFER.get(2));
				}
				if(t.getFromuid()==userInfo.getUserID()) {
					BankConsole.display(String.format(PROMPTS.PENDINGTRANSFER.get(3),t.getTransferID(),t.getFromaid(),t.getAmount(),t.getToaid()));
				}else {
					BankConsole.display(String.format(PROMPTS.PENDINGTRANSFER.get(1),t.getTransferID(),t.getFromaid(),t.getFromuid(),t.getAmount(),t.getToaid()));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void acceptTransfer() {
		SessionPOJO userInfo = (SessionPOJO) user;
		BankConsole.display(PROMPTS.ACCEPTTRANSFER);
		int tid = BankConsole.readI();
		BankConsole.display(PROMPTS.ACCEPTTRANSFER);
		String decision = BankConsole.read();
		switch(decision.toLowerCase()) {
			case "a":
			case "accept":
				decision="true";
				break;
			case "d":
			case "deny":
				decision="false";
				break;
			default:
				badInput();
		}
		TransferDAO t = new TransferDAO();
		try {
			t.update(tid+"",userInfo.getUserID()+"",decision);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
			case "amt": acceptTransfer();
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
