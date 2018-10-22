package banksys.control.exception;

public class BankTransactionException extends Exception {

	private static final long serialVersionUID = 1L;

	private Exception cause;

	private String message;

	public BankTransactionException(String message) {
		this.message = message;
	}

	public BankTransactionException(Exception cause) {
		super("Transaction not perfermed!");
		this.cause = cause;
	}

	public String getMessage() {
		String text = "Transaction not perfermed!\nCause: ";
		if (cause != null) {
			text += cause.getMessage();
		} else {
			text += message;
		}
		return text;
	}

	public Exception getCause() {
		return cause;
	}

}
