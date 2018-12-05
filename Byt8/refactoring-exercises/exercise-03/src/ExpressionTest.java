import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest {

	/*
	 * 
	 */
	
	@Test
	public void testConstant() {
		Expression e = new Expression(-43);
		assertEquals(-43, e.evaluate());
	}
	@Test
	public void testAddition() {
		Expression e = new Expression('+', new Expression(100), new Expression(
				-100));
		assertEquals(0, e.evaluate());
	}
	@Test
	public void testSubtraction() {
		Expression e = new Expression('-', new Expression(100), new Expression(
				-100));
		assertEquals(200, e.evaluate());
	}
	@Test
	public void testMultiplication() {
		Expression e = new Expression('*', new Expression(100), new Expression(
				-100));
		assertEquals(-10000, e.evaluate());
	}
	@Test
	public void testDivision() {
		Expression e = new Expression('/', new Expression(100), new Expression(
				-100));
		assertEquals(-1, e.evaluate());
	}
	@Test
	public void testComplexExpression() {
		// 1+2-3*4/5
		Expression e = new Expression('-', new Expression('+',
				new Expression(1), new Expression(2)), new Expression('/',
				new Expression('*', new Expression(3), new Expression(4)),
				new Expression(5)));
		assertEquals(1, e.evaluate());
	}

}
