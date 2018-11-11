package poo651;

import java.util.Stack;

public class MoteurRPN {
	private Stack<Double> pile;

	public MoteurRPN() {
		this.pile = new Stack<Double>();
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
			double a = pile.pop();
			double b = pile.pop();
		
			enregistrer(op.eval(a, b));
		}
	}

	public String toString() {
		return pile.toString();
	}
}