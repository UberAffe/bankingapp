package bankingapp.sessions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bankingapp.daos.AccountsDAO;
import bankingapp.daos.BasicDAO;
import bankingapp.daos.Credentials;
import bankingapp.daos.CustomerDAO;
import bankingapp.daos.EmployeeDAO;
import bankingapp.daos.SessionDAO;
import bankingapp.daos.UserDAO;
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
		case "view pending users":
		case "vpu": viewPendingUsers();
			break;
		case "2":
		case "view pending accounts":
		case "vpa": viewPendingAccounts();
			break;
		case "3":
		case "approve user":
		case "au": approveUser();
			break;
		case "4":
		case "approve account":
		case "aa": approveAccount();
			break;
		case "5":
		case "view a":
		case "vca": viewCustomerAccounts();
			break;
		case "6":
		case "view all":
		case "vt": viewAllTransactions();
			break;
		case "7":
		case "logout":
		case "l": logout();
			break;
		case "8":
		case "close":
		case "c": quit();
			break;
		default: badInput();
			break;
	}
	return keepGoing;
	}

	private void viewAllTransactions() {
		try(Connection conn = Credentials.getConnection();){
			int page = 1;
			PreparedStatement ps = conn.prepareStatement("select * from gettransactionpage(?)");
			ResultSet rs;
			
			ps.setInt(1, page);
			rs=ps.executeQuery();
			boolean wFlag = false;
			boolean dFlag = false;
			boolean tFlag = false;
			while(rs.next()) {
				if(rs.getInt(6)>0&&rs.getInt(3)>0) {
					//Transfer
					if(!tFlag) {
						tFlag=true;
						BankConsole.display("|Transaction #|Time of Transaction|From Account|From User|Transfered Amount| To Account | To User |");
					}
					BankConsole.display(String.format("|%13s|%19s|%12s|%9s|%17s|%12s|%9s|",
							rs.getInt(1)+"",rs.getDate(2)+"",rs.getInt(3)+"",rs.getInt(4)+"",rs.getFloat(5)+"",rs.getInt(6)+"",rs.getInt(7)+""));
				} else if(rs.getInt(6)>0) {
					//Deposit
					if(!dFlag) {
						dFlag=true;
						BankConsole.display("|Transaction #|Time of Transaction|Deposit Amount| Account | User |");
					}
					BankConsole.display(String.format("|%13s|%19s|%14s|%9s|%6s|",
							rs.getInt(1)+"",rs.getDate(2)+"",rs.getFloat(5)+"",rs.getInt(6)+"",rs.getInt(7)+""));
				} else {
					//Withdraw
					if(!wFlag) {
						wFlag=true;
						BankConsole.display("|Transaction #|Time of Transaction| Account | User |Withdrawn Amount|");
					}
					BankConsole.display(String.format("|%13s|%19s|%9s|%6s|%16s|",
							rs.getInt(1)+"",rs.getDate(2)+"",rs.getInt(3)+"",rs.getInt(4)+"",rs.getFloat(5)+""));
				}
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewCustomerAccounts() {
		BankConsole.display("Please enter the user ID of the customer whose accounts you want to see.");
		int uid = BankConsole.readI();
		ArrayList<AccountsDAO> accounts = AccountsDAO.getAccounts(uid, true);
		if(accounts.size()>0) {
			BankConsole.display(PROMPTS.ACCOUNT.get(0));
			for(AccountsDAO account:accounts) {
				BankConsole.display(String.format(PROMPTS.ACCOUNT.get(1),account.getType(),account.getID()+"",account.getBalance()+""));
			}
		}else {
			BankConsole.display("This user has no accounts.");
		}
	}

	private void approveAccount() {
		BankConsole.display("Please enter the account number you are deciding on.");
		int aid = BankConsole.readI();
		BankConsole.display("Enter 'approve' (a) to approve the account or 'deny' (d) to deny the application.");
		String decision = BankConsole.read();
		Boolean decide;
		switch(decision.toLowerCase()) {
			case "approve":
			case "a": decide = true;
				break;
			case "deny":
			case "d": decide = false;
				break;
			default: decide=null;
				badInput();
				break;
		}
		try(Connection conn = Credentials.getConnection();){
			if(decide) {
				CallableStatement cs = conn.prepareCall("call acceptaccount(?,?)");
				cs.setInt(1, aid);
				cs.setBoolean(2, decide);
				if(cs.execute()) {
					BankConsole.display("Account has been approved.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void approveUser() {
		BankConsole.display("Please enter the user id you are deciding on.");
		int aid = BankConsole.readI();
		BankConsole.display("Enter 'approve' (a) to approve the account or 'deny' (d) to deny the application.");
		String decision = BankConsole.read();
		Boolean decide;
		switch(decision.toLowerCase()) {
			case "approve":
			case "a": decide = true;
				break;
			case "deny":
			case "d": decide = false;
				break;
			default: decide=null;
				badInput();
				break;
		}
		try(Connection conn = Credentials.getConnection();){
			if(decide) {
				CallableStatement cs = conn.prepareCall("call acceptuser(?,?)");
				cs.setInt(1, aid);
				cs.setBoolean(2, decide);
				if(cs.execute()) {
					BankConsole.display("User has been approved.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewPendingAccounts() {
		try {
			ArrayList<BasicDAO> users = user.select();
			if(users.size()>0) {
				BankConsole.display("|UserID|Account #|Account Type|");
			
				for(BasicDAO b:users) {
					if(b instanceof CustomerDAO) {
						ArrayList<AccountsDAO> accounts =((CustomerDAO)b).getPendingAccounts();
						for(AccountsDAO account:accounts) {
							BankConsole.display(String.format("|{0,6}|{1,9}|{2,12}|",((CustomerDAO) b).getUserID(),account.getType()));
						}
					}
				}
			} else {
				BankConsole.display("There are no pending accounts.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewPendingUsers() {
		try {
			ArrayList<BasicDAO> rs = user.select();
			int count=0;
			for(BasicDAO u:rs) {
				if(!(u instanceof CustomerDAO) && !(u instanceof EmployeeDAO)) {
					BankConsole.display(String.format(PROMPTS.PENDINGUSER.toString(),((UserDAO)u).getUserID()));
					count+=1;
				}
			}
			if(count==0) {
				BankConsole.display("No users are awaiting approval.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
