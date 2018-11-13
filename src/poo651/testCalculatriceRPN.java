package poo651;

import static org.junit.Assert.*;

import org.junit.Test;

public class testCalculatriceRPN {
	
	@Test
	public void testOperateur() {
		assertEquals('+',Operation.PLUS.getSymbole(),0);
		assertEquals('-',Operation.MOINS.getSymbole(),0);
		assertEquals('/',Operation.DIV.getSymbole(),0);
		assertEquals('*',Operation.MULT.getSymbole(),0);
	}
	
	@Test
	public void testOperation() {
		double a = 3;
		double b = 3;
		try {
			assertEquals(6,Operation.PLUS.eval(a, b),0);
			assertEquals(0,Operation.MOINS.eval(a, b),0);
			assertEquals(1,Operation.DIV.eval(a, b),0);
			assertEquals(9,Operation.MULT.eval(a, b),0);
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
		assertEquals(0,moteurRPN.getPile().size(),0);
	}
	
	@Test
	public void testEnregistrer() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(5.0);
		assertEquals(1,moteurRPN.getPile().size(),0);
		moteurRPN.enregistrer(6.0);
		assertEquals(2,moteurRPN.getPile().size(),0);
	}
	
	@Test
	public void testAppliquer() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(3);
		moteurRPN.enregistrer(3);
		try {
			moteurRPN.appliquer(Operation.PLUS);
			Double a = moteurRPN.getPile().get(0);
			assertEquals(6,a,0);
			moteurRPN.enregistrer(1);
			moteurRPN.appliquer(Operation.MOINS);
			Double b = moteurRPN.getPile().get(0);
			assertEquals(5,b,0);
			moteurRPN.enregistrer(2);
			moteurRPN.appliquer(Operation.MULT);
			Double c = moteurRPN.getPile().get(0);
			assertEquals(10,c,0);
			moteurRPN.enregistrer(2);
			moteurRPN.appliquer(Operation.DIV);
			Double d = moteurRPN.getPile().get(0);
			assertEquals(5,d,0);
		}catch (NonDivisibleException e) {
		}catch (PileException e) {
		}catch (HorsBornesException e) {}
	}
	
}
