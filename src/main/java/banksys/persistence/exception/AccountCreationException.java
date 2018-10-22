package banksys.persistence.exception;

public class AccountCreationException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public AccountCreationException(String message, String number) {
		super(message, number);
	}
}
