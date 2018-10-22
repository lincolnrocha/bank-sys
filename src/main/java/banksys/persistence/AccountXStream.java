package banksys.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;
import banksys.persistence.exception.FlushException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AccountXStream implements IAccountRepository {

	public static final String ACCOUNT_DB_XML_NAME = "AccountDB.xml";

	private Vector<AbstractAccount> accounts = null;

	public AccountXStream() {
		try {
			loadData();
		} catch (FileNotFoundException fnfe) {
			this.accounts = new Vector<AbstractAccount>();
			try {
				saveData();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		AbstractAccount account = this.findAccount(number);
		if (account != null) {
			this.accounts.remove(account);
			try {
				saveData();
			} catch (IOException ioe) {
				throw new AccountDeletionException("Account could not be deleted! " + ioe.getMessage(), number);
			}
		} else {
			throw new AccountDeletionException("Account doesn't exist!", number);
		}
	}

	public void create(AbstractAccount account) throws AccountCreationException {
		if (this.findAccount(account.getNumber()) == null) {
			this.accounts.addElement(account);
			try {
				saveData();
			} catch (IOException ioe) {
				throw new AccountCreationException("Account could not be created! " + ioe.getMessage(),
						account.getNumber());
			}
		} else {
			throw new AccountCreationException("Account alredy exist!", account.getNumber());
		}
	}

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

	public int mumberOfAccounts() {
		return this.accounts.size();
	}

	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		AbstractAccount account = findAccount(number);
		if (account != null) {
			return account;
		} else {
			throw new AccountNotFoundException("Account not found!", number);
		}
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

	@SuppressWarnings("unchecked")
	private void loadData() throws FileNotFoundException {

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("account", AbstractAccount.class);
		accounts = ((Vector<AbstractAccount>) xstream.fromXML(new FileReader(ACCOUNT_DB_XML_NAME), Vector.class));
		System.out.println(accounts.size());
	}

	private void saveData() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("account", AbstractAccount.class);
		xstream.toXML(accounts, new FileWriter(ACCOUNT_DB_XML_NAME));
	}

	@Override
	public void flush() throws FlushException {
		try {
			saveData();
		} catch (IOException ioe) {
			throw new FlushException("Repository flush error! " + ioe.getMessage());
		}
	}
}
