package banksys.account;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public abstract class AbstractAccount {

	protected String number;
	protected double balance;

	public AbstractAccount(String number) {
		this.number = number;
		this.balance = 0;
	}

	public void credit(double amount) throws NegativeAmountException {
		if (amount > 0) {
			this.balance -= amount;
		} else {
			throw new NegativeAmountException(amount);
		}
	}

	public abstract void debit(double amount) throws NegativeAmountException, InsufficientFundsException;

	public String getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}
}