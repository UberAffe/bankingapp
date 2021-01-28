package bankingapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bankingapp.pojos.SessionPOJO;

public class EmployeeDAO extends SessionPOJO implements SessionDAO {

	public EmployeeDAO(int userID, String un, String pw) {
		super(userID,un,pw);
	}

	@Override
	public void insert(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(String ...args) throws SQLException {
		return false;
	}

	@Override
	public void delete(String ...args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<BasicDAO> select(String ...args) throws SQLException {
		ArrayList<BasicDAO> aList = new ArrayList<BasicDAO>();
		try(Connection conn = Credentials.getConnection();){
			PreparedStatement ps = conn.prepareStatement("select * from getuserids()");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				aList.add(DAOFactory.getUser(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5)));
			}
		}
		return aList;
	}

	@Override
	public void applyForAccount(String string, float parseFloat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<AccountsDAO> getPendingAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountsDAO> getActiveAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
