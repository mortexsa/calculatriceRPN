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
	
	@Test
	public void testNonDivMoteur() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(5);
		moteurRPN.enregistrer(0);
		try {
			moteurRPN.appliquer(Operation.DIV);
		} catch (NonDivisibleException e) {
			assert e.getMessage().equals("On ne peut diviser avec 0");
		}catch (HorsBornesException e) {
		}catch (PileException e) {}
	}
	
	@Test
	public void testPileVide() {
		MoteurRPN moteurRPN = new MoteurRPN();
		try {
			moteurRPN.appliquer(Operation.DIV);
		}catch (PileException e) {
			assert e.getMessage().equals("La pile est vide");
		}catch (HorsBornesException e) {
		}catch (NonDivisibleException e) {}
	}
	
	@Test
	public void testPileInsuffisante() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(5);
		try {
			moteurRPN.appliquer(Operation.DIV);
		}catch (PileException e) {
			assert e.getMessage().equals("Il n'y a qu'un seul nombre");
		}catch (HorsBornesException e) {
		}catch (NonDivisibleException e) {}
	}
	
	@Test
	public void testHorsBorneExceptionMax() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(SaisieRPN.MAX_VALUE);
		moteurRPN.enregistrer(SaisieRPN.MAX_VALUE);
		try {
			moteurRPN.appliquer(Operation.PLUS);
		}catch (PileException e) {
		}catch (HorsBornesException e) {
			assert e.getMessage().equals("La valeur doit etre un nombre entre "+SaisieRPN.MIN_VALUE+" et "+SaisieRPN.MAX_VALUE);
		}catch (NonDivisibleException e) {}
	}
	
	@Test
	public void testHorsBorneExceptionMin() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(SaisieRPN.MIN_VALUE);
		moteurRPN.enregistrer(100);
		try {
			moteurRPN.appliquer(Operation.DIV);
		}catch (PileException e) {
		}catch (HorsBornesException e) {
			assert e.getMessage().equals("La valeur doit etre un nombre entre "+SaisieRPN.MIN_VALUE+" et "+SaisieRPN.MAX_VALUE);
		}catch (NonDivisibleException e) {}
	}
	
	@Test
	public void testToString() {
		MoteurRPN moteurRPN = new MoteurRPN();
		moteurRPN.enregistrer(100);
		assert moteurRPN.toString().equals("[100.0]");
	}
}
