package bankingapp.pojos;

import java.util.ArrayList;

public class AccountPOJO {

	private int id;
	private double balance;
	private String type;
	private ArrayList<Integer> assocIDs = new ArrayList<Integer>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Integer> getAssocIDs() {
		return assocIDs;
	}
	public void setAssocIDs(ArrayList<Integer> assocIDs) {
		this.assocIDs = assocIDs;
	}
}
