package bankingapp.exceptions;

public class BankException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXCEPT reason;
	public boolean badlog=false;

	public BankException(boolean log) {
		badlog=log;
	}
	
	public BankException(EXCEPT reason) {
		this.reason = reason;
	}
}
