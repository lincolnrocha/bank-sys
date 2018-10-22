package banksys.persistence.exception;

public class AccountDeletionException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public AccountDeletionException(String message, String number) {
		super(message, number);
	}
}
