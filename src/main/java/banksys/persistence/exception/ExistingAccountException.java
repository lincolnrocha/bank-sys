package banksys.persistence.exception;

public class ExistingAccountException extends Exception {

	private static final long serialVersionUID = 1L;

	private String number;

	public ExistingAccountException(String number) {
		super("Existing OrdinaryAccount!");
		this.number = number;
	}

	public String getMessage() {
		return "Existing OrdinaryAccount! account number = " + number;
	}

	public String getNumber() {
		return number;
	}

}
