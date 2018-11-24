package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}
	
	/*
	 * Checking getName() method
	 */
	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	/*
	 * Checking getCurrency() method
	 */
	@Test
	public void testGetCurrency() {
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	/*
	 * Checking openAccount() method
	 */
	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("test1");
		assertTrue(SweBank.existsAccount("test1"));
	}

	/*
	 * Checking deposit() method
	 */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		assertEquals(1000, SweBank.getBalance("Bob"), 0);
	}

	/*
	 * Checking withdraw() method
	 */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Bob", new Money(1000, SEK));
		assertEquals(-1000, SweBank.getBalance("Bob"), 0);
	}
	
	/*
	 * Checking getBalance() method
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0, SweBank.getBalance("Bob"), 0);
	}
	/*
	 * Checking transfer() method inside same bank and with other bank
	 */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		SweBank.transfer("Bob", "Ulrika", new Money(500, SEK));		// transfer inside same bank
		assertEquals(500, SweBank.getBalance("Bob"), 0);
		assertEquals(500, SweBank.getBalance("Ulrika"), 0);
		
		SweBank.transfer("Bob", DanskeBank, "Gertrud", new Money(500, SEK));		// transfer between banks
		assertEquals(0, SweBank.getBalance("Bob"), 0);
		assertEquals((int)(500 * 0.15 / 0.20), DanskeBank.getBalance("Gertrud"), 0);
	}
	
	/*
	 * Checking addTimedPayment() method
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		SweBank.addTimedPayment("Bob", "1", 2, 0, new Money(1000, SEK), SweBank, "Ulrika"); 	// Adding TimedPayment
		SweBank.tick();																			// Ticking to proceed payment
		
		assertEquals(0, SweBank.getBalance("Bob"), 0);
		assertEquals(1000, SweBank.getBalance("Ulrika"), 0);
		
		
	}
}
