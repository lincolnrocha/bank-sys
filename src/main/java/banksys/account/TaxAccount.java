package banksys.account;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class TaxAccount extends AbstractAccount {

	public TaxAccount(String number) {
		super(number);
	}

	public void debit(double amount) throws NegativeAmountException, InsufficientFundsException {
		if (amount > 0) {
			if (this.balance < (amount + (amount * 0.001))) {
				this.balance = this.balance - (amount + (amount * 0.001));
			} else {
				throw new InsufficientFundsException(number, amount);
			}
		} else {
			throw new NegativeAmountException(amount);
		}
	}
}
