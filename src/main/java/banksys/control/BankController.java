package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;
import banksys.persistence.exception.FlushException;

public class BankController {

	private IAccountRepository repository;

	public BankController(IAccountRepository repository) {
		this.repository = repository;
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException {
		try {
			this.repository.create(account);
		} catch (AccountCreationException ace) {
			throw new BankTransactionException(ace);
		}
		this.commit();
	}

	public void removeAccount(String number) throws BankTransactionException {
		try {
			this.repository.delete(number);
		} catch (AccountDeletionException ade) {
			throw new BankTransactionException(ade);
		}
		this.commit();
	}

	public void doCredit(String number, double amount) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		try {
			account.credit(amount);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
		this.commit();

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		try {
			account.debit(amount);
		} catch (InsufficientFundsException ife) {
			throw new BankTransactionException(ife);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
		this.commit();
	}

	public double getBalance(String number) throws BankTransactionException {
		AbstractAccount conta;
		try {
			conta = this.repository.retrieve(number);
			return conta.getBalance();
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException {
		AbstractAccount fromAccount;
		try {
			fromAccount = this.repository.retrieve(fromNumber);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		AbstractAccount toAccount;
		try {
			toAccount = this.repository.retrieve(fromNumber);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		try {
			fromAccount.debit(amount);
			toAccount.credit(amount);
		} catch (InsufficientFundsException sie) {
			throw new BankTransactionException(sie);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}

		this.commit();
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SavingsAccount) {
			((SavingsAccount) auxAccount).earnInterest();
		} else {
			throw new IncompatibleAccountException(number);
		}

		this.commit();
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SpecialAccount) {
			((SpecialAccount) auxAccount).earnBonus();
		} else {
			throw new IncompatibleAccountException(number);
		}
		this.commit();
	}

	private void commit() throws BankTransactionException {
		try {
			this.repository.flush();
		} catch (FlushException fe) {
			throw new BankTransactionException(fe);
		}
	}
}
