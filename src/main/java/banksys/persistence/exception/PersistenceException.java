package banksys.persistence.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String number;

	protected String message;

	public PersistenceException(String message) {
		super("Persistence Exception!");
		this.message = message;
	}

	public PersistenceException(String message, String number) {
		super("Persistence Exception!");
		this.number = number;
		this.message = message;
	}

	public String getMessage() {
		return this.message + " [account number = " + number + "]";
	}

	public String getNumber() {
		return number;
	}
}