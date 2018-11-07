package poo651;

import java.util.Stack;

public class MoteurRPN {
	private Stack<Double> pile;
	public MoteurRPN() {
		pile = new Stack<Double>();
	}
	
	public void enregistrer(double operande) {
		pile.push(operande);
	}
	
	public void appliquer(Operation op) {
		if(pile.size() <= 2) {
			double a = pile.pop();
			double b = pile.pop();
			enregistrer(op.eval(a, b));
		}
	}
	
	public String toString() {
		String contenu = pile.toString();
		System.out.println(contenu);
		return contenu;
	}
}