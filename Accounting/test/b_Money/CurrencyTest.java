package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	/*
	 * Testing getName() method, that should return name of the currency
	 */
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	/*
	 * Testing getRate() method that should return rate of the currency
	 */
	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0);
		assertEquals(0.20, DKK.getRate(), 0);
		assertEquals(1.5, EUR.getRate(), 0);
	}
	/*
	 * Testing setRate() method that sets rate with new value
	 */
	@Test
	public void testSetRate() {
		SEK.setRate(0.1);
		assertEquals(0.1, SEK.getRate(), 0);
	}
	/*
	 *  Testing universalValue() method that return universal value of this currency (amount * rate)
	 */
	@Test
	public void testGlobalValue() {
		assertEquals(100 * 0.20, DKK.universalValue(100), 0);
	}
	/*
	 * Test method valueInThisCurrency() that returns value of other currency in our currency
	 */
	@Test
	public void testValueInThisCurrency() {
		assertEquals((int)(100 * 1.5 / 0.20), DKK.valueInThisCurrency(100, EUR), 0);
		
	}

}
