package banksys.view;

import java.util.Scanner;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.TaxAccount;
import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountVector;

public class ATM24H {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		BankController bank = new BankController(new AccountVector());
		boolean loop = true;
		while (loop) {
			switch (mainMenu()) {
			case 1:
				AbstractAccount account = null;
				switch (addAccountMenu()) {
				case 1:
					System.out.println("Enter the ordinary account number: ");
					account = new OrdinaryAccount(scanner.next());
					break;
				case 2:
					System.out.println("Enter the special account number: ");
					account = new SpecialAccount(scanner.next());
					break;
				case 3:
					System.out.println("Enter the saving account number: ");
					account = new SavingsAccount(scanner.next());
					break;
				case 4:
					System.out.println("Enter the tax account number: ");
					account = new TaxAccount(scanner.next());
					break;

				default:
					System.out.println("Invalid option!!!!");
					break;
				}

				if (account != null) {
					try {
						bank.addAccount(account);
						System.out.println("Operation was successful!");
					} catch (BankTransactionException bte) {
						System.out.println("Error: " + bte.getMessage());
					}
				}

				break;
			case 2:
				System.out.println("Enter the account number: ");
				String number = scanner.next();
				System.out.println("Enter the amount to be credited: ");
				double amount = scanner.nextDouble();
				try {
					bank.doCredit(number, amount);
					System.out.println("Operation was successful!");
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}

				break;
			case 3:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				System.out.println("Enter the amount to be debited: ");
				amount = scanner.nextDouble();
				try {
					bank.doDebit(number, amount);
					System.out.println("Operation was successful!");
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}

				break;
			case 4:
				System.out.println("Enter the origin account number: ");
				String fromNumber = scanner.next();
				System.out.println("Enter the destination account number: ");
				String toNumber = scanner.next();
				System.out.println("Enter the amount to be transferred: ");
				amount = scanner.nextDouble();

				try {
					bank.doTransfer(fromNumber, toNumber, amount);
					System.out.println("Operation was successful!");
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}

				break;
			case 5:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				try {
					System.out.println("Account number: " + number);
					System.out.println("Balance: " + bank.getBalance(number));
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				try {
					bank.removeAccount(number);
					System.out.println("Operation was successful!");
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}
				break;
			case 7:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				try {
					bank.doEarnInterest(number);
					System.out.println("Operation was successful!");
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}

				break;
			case 8:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				try {
					bank.doEarnBonus(number);
					System.out.println("Operation was successful!");
				} catch (BankTransactionException bte) {
					System.out.println("Error: " + bte.getMessage());
				}
				break;

			case 9:
				System.out.print("Goodbye and have a nice day!!!");
				loop = false;
				break;

			default:
				break;
			}
		}
	}

	private static int mainMenu() {
		System.out.println("================================");
		System.out.println("Wellcome to the Our Bank");
		System.out.println("Automated Teller Machine");
		System.out.println("================================");
		System.out.println(" [1] New Account");
		System.out.println(" [2] Do Credit");
		System.out.println(" [3] Do Debit");
		System.out.println(" [4] Do Transfer");
		System.out.println(" [5] Show Balance");
		System.out.println(" [6] Remove Account");
		System.out.println(" [7] Earn Iterest");
		System.out.println(" [8] Earn Bonus");
		System.out.println(" [9] Exit");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();

	}

	private static int addAccountMenu() {
		System.out.println("================================");
		System.out.println("Add New Account");
		System.out.println("================================");
		System.out.println(" [1] Ordinary");
		System.out.println(" [2] Special");
		System.out.println(" [3] Savings");
		System.out.println(" [4] Tax");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();
	}

}