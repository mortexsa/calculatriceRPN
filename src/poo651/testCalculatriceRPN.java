package poo651;

import static org.junit.Assert.*;

import org.junit.Test;

public class testCalculatriceRPN {
	
	@Test
	public void testOperateur() {
		assertEquals(Operation.PLUS.getSymbole(), '+',0);
		assertEquals(Operation.MOINS.getSymbole(), '-',0);
		assertEquals(Operation.DIV.getSymbole(), '/',0);
		assertEquals(Operation.MULT.getSymbole(), '*',0);
	}
	
	@Test
	public void testOperation() {
		double a = 3;
		double b = 3;
		try {
			assertEquals(Operation.PLUS.eval(a, b),6,0);
			assertEquals(Operation.MOINS.eval(a, b),0,0);
			assertEquals(Operation.DIV.eval(a, b),1,0);
			assertEquals(Operation.MULT.eval(a, b),9,0);
		}catch (NonDivisibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDivParZero() {
		double a = 5;
		double b = 0;
		try {
			Operation.DIV.eval(a, b);
		}catch (NonDivisibleException e) {
			assert e.getMessage().equals("On ne peut diviser avec 0");
		}
	}
	
	@Test
	public void testMoteurRPN() {
		MoteurRPN moteurRPN = new MoteurRPN();
		assertNotNull(moteurRPN);
		assertEquals(moteurRPN.getPile().size(),0,0);
	}
	
	@Test
	public void testEnregistrer() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(5.0);
		assertEquals(moteurRPN.getPile().size(), 1,0);
		moteurRPN.enregistrer(6.0);
		assertEquals(moteurRPN.getPile().size(), 2,0);
	}
	
}
