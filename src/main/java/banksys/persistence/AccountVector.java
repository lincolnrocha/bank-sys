package banksys.persistence;

import java.util.Vector;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.FlushException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVector implements IAccountRepository {

	private final Vector<AbstractAccount> accounts;

	public AccountVector() {
		this.accounts = new Vector<AbstractAccount>();
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		AbstractAccount account = this.findAccount(number);
		if (account != null) {
			this.accounts.remove(account);
		} else {
			throw new AccountDeletionException("Account doesn't exist!", number);
		}
	}

	@Override
	public void create(AbstractAccount account) throws AccountCreationException {
		if (this.findAccount(account.getNumber()) == null) {
			this.accounts.addElement(account);
		} else {
			throw new AccountCreationException("Account alredy exist!", account.getNumber());
		}
	}

	@Override
	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		AbstractAccount account = findAccount(number);
		if (account != null) {
			return account;
		} else {
			throw new AccountNotFoundException("Account not found!", number);
		}
	}

	@Override
	public int mumberOfAccounts() {
		return this.accounts.size();
	}

	@Override
	public AbstractAccount[] list() {
		AbstractAccount[] list = null;
		if (this.accounts.size() > 0) {
			list = new AbstractAccount[this.accounts.size()];
			for (int i = 0; i < this.accounts.size(); i++) {
				list[i] = (AbstractAccount) this.accounts.elementAt(i);
			}
		}
		return list;
	}

	@Override
	public void flush() throws FlushException {
	}

	private AbstractAccount findAccount(String number) {
		if (this.accounts.size() > 0) {
			for (int i = 0; i < this.accounts.size(); i++) {
				AbstractAccount account = (AbstractAccount) this.accounts.elementAt(i);
				if (account.getNumber().equals(number)) {
					return account;
				}
			}
		}
		return null;
	}
}