package bankingapp.pojos;

public class TransferPOJO {

	protected int fromuid=0;
	protected int fromaid=0;
	protected float amount=0;
	protected int touid=0;
	protected int toaid=0;
	protected int transferID=-1;
	public int getTransferID() {
		return transferID;
	}
	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}
	public TransferPOJO() {};
	public TransferPOJO(int transferID,int fromuid, int fromaid, float amount, int toaid) {
		super();
		this.transferID = transferID;
		this.fromuid = fromuid;
		this.fromaid = fromaid;
		this.amount = amount;
		this.toaid = toaid;
	}
	public int getFromuid() {
		return fromuid;
	}
	public void setFromuid(int fromuid) {
		this.fromuid = fromuid;
	}
	public int getFromaid() {
		return fromaid;
	}
	public void setFromaid(int fromaid) {
		this.fromaid = fromaid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getTouid() {
		return touid;
	}
	public void setTouid(int touid) {
		this.touid = touid;
	}
	public int getToaid() {
		return toaid;
	}
	public void setToaid(int toaid) {
		this.toaid = toaid;
	}
	
}
