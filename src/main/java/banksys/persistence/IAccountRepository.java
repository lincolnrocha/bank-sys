package banksys.persistence;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.FlushException;
import banksys.persistence.exception.AccountNotFoundException;

public interface IAccountRepository {

	public void create(AbstractAccount account) throws AccountCreationException;

	public void delete(String number) throws AccountDeletionException;

	public AbstractAccount retrieve(String number) throws AccountNotFoundException;

	public AbstractAccount[] list();

	public int mumberOfAccounts();

	public void flush() throws FlushException;
}
