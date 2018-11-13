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

	public void appliquer(Operation op) throws PileException, NonDivisibleException, HorsBornesException {

		if (pile.isEmpty()) {
			throw new PileVideException();
		}
		if (pile.size() == 1)
			throw new PileInsuffisanteException();
		else {
			double a = pile.pop();
			double b = pile.pop();
			if (Math.abs(op.eval(b, a)) < SaisieRPN.MIN_VALUE || Math.abs(op.eval(b, a)) > SaisieRPN.MAX_VALUE) {
				pile.push(b);
				pile.push(a);
				throw new HorsBornesException("La valeur doit etre un nombre entre " + SaisieRPN.MIN_VALUE + "et " + SaisieRPN.MAX_VALUE);
			}
			else
			{
				enregistrer(op.eval(a, b));
			}
		}
	}

	public String toString() {
		return pile.toString();
	}
}