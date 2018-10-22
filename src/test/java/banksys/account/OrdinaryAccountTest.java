package banksys.account;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class OrdinaryAccountTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreditAndDebit() {
		OrdinaryAccount account = new OrdinaryAccount("123A");
		try {
			account.credit(50);
			assertEquals(50, account.getBalance(), 0.0);
			try {
				account.debit(30);
				assertEquals(20, account.getBalance(), 0.0);
			} catch (InsufficientFundsException e) {
				fail(e.getMessage());
			}
		} catch (NegativeAmountException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = InsufficientFundsException.class)
	public void testDebitWithInsufficientFunds() throws NegativeAmountException, InsufficientFundsException {
		OrdinaryAccount account = new OrdinaryAccount("12345X");
		account.debit(100);
	}

	@Test(expected = NegativeAmountException.class)
	public void testDebitWithNegativeAmount() throws NegativeAmountException, InsufficientFundsException {
		OrdinaryAccount account = new OrdinaryAccount("12345X");
		account.debit(-100);
	}

	@Test
	public void testCreditWithPositiveAmount() throws NegativeAmountException {
		OrdinaryAccount account = new OrdinaryAccount("12345X");
		account.credit(100);
		assertEquals(100, account.getBalance(), 0.0);
	}

	@Test(expected = NegativeAmountException.class)
	public void testCreditWithNegativeAmount() throws NegativeAmountException {
		OrdinaryAccount account = new OrdinaryAccount("12345X");
		account.credit(-100);
	}

	@Test
	public void testGetNumber() {
		OrdinaryAccount account = new OrdinaryAccount("12345X");
		assertEquals("12345X", account.getNumber());
	}

	@Test
	public void testGetBalance() {
		OrdinaryAccount account = new OrdinaryAccount("12345X");
		assertEquals(0.0, account.getBalance(), 0.0);
	}

}
