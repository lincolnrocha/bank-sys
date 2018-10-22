package banksys.account.exception;

public class NegativeAmountException extends Exception {

	private static final long serialVersionUID = 1L;

	private double amount;

	public NegativeAmountException(double amount) {
		super("Negative amount!");
		this.amount = amount;
	}

	public String getMessage() {
		return "Negative amount! [amount = " + amount + "]";
	}

	public double getAmount() {
		return amount;
	}

}
