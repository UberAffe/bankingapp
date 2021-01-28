package bankingapp.pojos;

public class SessionPOJO {

	protected int userID;
	
	public SessionPOJO(int userID2, String un, String pw) {
		this.userID=userID2;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
