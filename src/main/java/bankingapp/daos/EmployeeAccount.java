package bankingapp.daos;

public class EmployeeAccount extends SessionDAO {

	public EmployeeAccount(int userID, String un, String pw) {
		super(userID,un,pw);
	}

}
