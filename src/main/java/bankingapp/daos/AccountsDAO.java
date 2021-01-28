package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bankingapp.pojos.AccountPOJO;
import bankingapp.utils.BankLog;

public class AccountsDAO extends AccountPOJO implements BasicDAO{
	
	public AccountsDAO(int uid, int aid, float amount, String t) {
		super();
		id=aid;
		assocIDs.add(uid);
		balance=amount;
		type=t;
	}
	
	public float getBalance() {return balance;}
	public String getType() {return type;}
	public int getID() {return id;}
	

	public static AccountsDAO applyForAccount(int uid, String type, float amount) {
		AccountsDAO temp = null;
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs = conn.prepareCall("registeraccount(?, ?, ?)");
			cs.setInt(1, uid);
			cs.setFloat(2, amount);
			cs.setString(3, type);
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
			PreparedStatement ps = conn.prepareStatement("select * from getuseraccounts(?,?)");
			ps.setInt(1, userID);
			ps.setBoolean(2, pending);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accounts.add(new AccountsDAO(userID,rs.getInt(1),(float)(Math.floor(rs.getFloat(2)*100)/100),rs.getString(3)));
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
			float amount = (float)(Math.floor(Float.parseFloat(args[1])*100)/100);
			if(args[2].equals("deposit")) {
				cs= conn.prepareCall("call deposit(?,?,?)");
			} else if(args[2].equals("withdraw")) {
				cs= conn.prepareCall("call withdraw(?,?,?)");
			}
			cs.setInt(1, uid);
			cs.setInt(2, id);
			cs.registerOutParameter(3, Types.REAL);
			cs.setFloat(3, amount);
			cs.execute();
			Float res = cs.getFloat(3);
			if(res>=0) {
				balance=res;
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
