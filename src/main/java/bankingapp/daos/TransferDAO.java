package bankingapp.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bankingapp.pojos.TransferPOJO;
import bankingapp.utils.BankLog;

public class TransferDAO extends TransferPOJO implements BasicDAO{
	
	public TransferDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransferDAO(int fromuid, int fromaid, double amount, int toaid) {
		super(-1, fromuid, fromaid, amount, toaid);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void insert(String... args) throws SQLException {
		if(fromuid!=0 && fromaid!=0 && amount!=0 && toaid!=0) {
			try(Connection conn = Credentials.getConnection();){
				CallableStatement cs = conn.prepareCall("call posttransfer(?,?,?,?)");
				cs.setInt(1, fromuid);
				cs.setInt(2, fromaid);
				cs.setDouble(3, amount);
				cs.setInt(4, toaid);
				cs.execute();
			}
		}
	}
	@Override
	public boolean update(String... args) throws SQLException {
		int tid = Integer.parseInt(args[0]);
		int uid = Integer.parseInt(args[1]);
		boolean decision = Boolean.parseBoolean(args[2]);
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs = conn.prepareCall("call accepttransfer(?,?,?)");
			cs.setInt(1, tid);
			cs.setInt(2, uid);
			cs.setBoolean(3, decision);
			return cs.execute();
		}
	}
	@Override
	public void delete(String... args) throws SQLException {
		// TODO Auto-generated method stub
		BankLog.warn("Not sure how you got to TransferDAO's delete method, but you shouldn't be here.");
	}
	@Override
	public ArrayList<TransferPOJO> select(String... args) throws SQLException {
		int uid = Integer.parseInt(args[0]);
		try(Connection conn = Credentials.getConnection();){
			CallableStatement cs = conn.prepareCall("?= call getpendingtransfers(?)");
			cs.setInt(2, uid);
			ResultSet rs= cs.executeQuery();
			ArrayList<TransferPOJO> transfers= new ArrayList<TransferPOJO>();
			while(rs.next()) {
				transfers.add(new TransferPOJO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5)));
			}
			return transfers;
		}
	}
	
	
	
}
