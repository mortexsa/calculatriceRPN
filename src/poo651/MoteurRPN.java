package poo651;

import java.util.Stack;

public class MoteurRPN {
	private Stack<Double> pile;

	public MoteurRPN() {
		this.pile = new Stack<Double>();
	}
	
	public Stack<Double> getPile() {
		return pile;
	}
	
	public void enregistrer(double operande) {
		pile.push(operande);
	}

	public void appliquer(Operation op) throws PileException, NonDivisibleException {
		
		if (pile.isEmpty())
		{
			throw new PileVideException();	
		}
		if (pile.size() == 1)
			throw new PileInsuffisanteException();
		else {
			Double a = pile.pop();
			Double b = pile.pop();
			try {
				enregistrer(op.eval(b, a));
			} catch (NonDivisibleException e) {
				pile.push(b);
				pile.push(a);
				System.out.println(e.getMessage());
			}
			
		}
	}

	public String toString() {
		return pile.toString();
	}
}