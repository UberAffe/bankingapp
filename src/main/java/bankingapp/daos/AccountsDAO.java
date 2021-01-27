package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bankingapp.pojos.AccountPOJO;
import bankingapp.utils.BankLog;

public class AccountsDAO extends AccountPOJO implements BasicDAO{

	private int id;
	private double balance;
	private String type;
	private ArrayList<Integer> assocIDs = new ArrayList<Integer>();
	
	public AccountsDAO(int uid, int aid, double amount, String t) {
		super();
		id=aid;
		assocIDs.add(uid);
		balance=amount;
		type=t;
	}
	
	public double getBalance() {return balance;}
	public String getType() {return type;}
	public int getID() {return id;}
	

	public static AccountsDAO applyForAccount(int uid, String type, double amount) {
		AccountsDAO temp = null;
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs = conn.prepareCall("?= registeraccount(?, ?, ?)");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, uid);
			cs.setDouble(3, amount);
			cs.setString(4, type);
			if(!cs.execute()) {
				BankLog.warn("Account was not created for authorization.");
			}
			temp = new AccountsDAO(uid,cs.getInt(1),amount,type);
			BankLog.info("Created new account with id "+temp.id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			BankLog.warn(e.getMessage());
		}
		return temp;
	}

	public static ArrayList<AccountsDAO> getAccounts(int userID, boolean pending) {
		ArrayList<AccountsDAO> accounts = new ArrayList<AccountsDAO>();
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs = conn.prepareCall("call getuseraccounts(?,?)");
			cs.setInt(1, userID);
			cs.setBoolean(2, pending);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				accounts.add(new AccountsDAO(userID,rs.getInt(1),rs.getDouble(2),rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void insert(String ...args) throws SQLException {
		//Unused method
		BankLog.warn("I don't know how you got to the insert of AccountsDAO, but you shouldn't be here.");
	}

	@Override
	public boolean update(String ...args) throws SQLException {
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs=null;
			int uid = Integer.parseInt(args[0]);
			double amount = Double.parseDouble(args[1]);
			if(amount>0) {
				cs= conn.prepareCall("call deposit(?,?,?)");
			} else if(amount<0) {
				cs= conn.prepareCall("call withdraw(?,?,?");
			}
			cs.setInt(1, uid);
			cs.setInt(2, id);
			cs.registerOutParameter(3, Types.DOUBLE);
			cs.setDouble(3, amount);
			cs.execute();
			Double res = cs.getDouble(3);
			if(res>=0) {
				balance=cs.getDouble(3);
				return true;
			} 
		}
		return false;
	}

	@Override
	public void delete(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList select(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
