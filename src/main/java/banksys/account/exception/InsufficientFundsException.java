package banksys.account.exception;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String number;

	private double balance;

	public InsufficientFundsException(String number, double balance) {
		super("Insufficient funds!");
		this.number = number;
		this.balance = balance;

	}

	public String getMessage() {
		return "Insufficient funds! [account number = " + number + " balance = " + balance + "]";
	}

	public String getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}

}
