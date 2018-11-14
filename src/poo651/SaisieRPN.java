package poo651;

import java.util.Scanner;

public class SaisieRPN {
	public static final double MIN_VALUE = 0.000001;
	public static final double MAX_VALUE = 10000000;

	private MoteurRPN moteurRPN;

	public SaisieRPN() {
		this.moteurRPN = new MoteurRPN();
	}

	public void entrerSaisie() throws Exception {
		Double operande;

		System.out.println("entrez un nombre ou un operande ou quittez en ecrivant 'exit':");
		Scanner scan = new Scanner(System.in);

		String input = scan.nextLine();

		while (!input.equals("exit")) {
			try {
				if (input.length() == 0)
					throw new HorsBornesException("L'input ne peut pas etre vide");
				if (input.equals("+")) {
					moteurRPN.appliquer(Operation.PLUS);
				} else if (input.equals("-")) {
					moteurRPN.appliquer(Operation.MOINS);
				} else if (input.equals("*")) {
					moteurRPN.appliquer(Operation.MULT);
				} else if (input.equals("/")) {
					moteurRPN.appliquer(Operation.DIV);
				} else {
					operande = Double.parseDouble(input);
					if (Math.abs(operande) >= MIN_VALUE && Math.abs(operande) <= MAX_VALUE) {
						moteurRPN.enregistrer(operande);
					} else
						throw new HorsBornesException("La valeur doit etre un nombre entre la valeur absolue de "
								+ MIN_VALUE + " et la valeur absolue de " + MAX_VALUE);
				}
			} catch (PileException e) {
				System.out.println(e.getMessage());
			} catch (NonDivisibleException e) {
				System.out.println(e.getMessage());
			} catch (HorsBornesException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("Le input doit etre un nombre ou un symbole d'operation");
			} finally {
				System.out.println(moteurRPN.toString());
				input = scan.nextLine();
			}
		}
		scan.close();
	}
}
