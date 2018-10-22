package banksys.account;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class OrdinaryAccount extends AbstractAccount {

	public OrdinaryAccount(String number) {
		super(number);
	}

	public void debit(double amount) throws NegativeAmountException, InsufficientFundsException {
		if (amount > 0) {
			if (this.balance < amount) {
				this.balance = this.balance - amount;
			} else {
				throw new InsufficientFundsException(number, amount);
			}
		} else {
			throw new NegativeAmountException(amount);
		}
	}
}