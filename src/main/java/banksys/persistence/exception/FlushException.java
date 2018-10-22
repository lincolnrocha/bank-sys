package banksys.persistence.exception;

public class FlushException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public FlushException(String message) {
		super(message);
	}

	public String getMessage() {
		return this.message;
	}
}
