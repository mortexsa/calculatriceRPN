package poo651;

import java.util.Scanner;

public class SaisieRPN {
	private static final double MIN_VALUE = 0;
	private static final double MAX_VALUE = 10000000;
	
	public void entrerSaisie() {
		MoteurRPN moteurRPN = new MoteurRPN();
		double operande;
		
		System.out.println("entrez un nombre ou un operande ou quittez en ecrivant 'exit':");
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		
		while(!input.equals("exit")) {
			try {
				if(input.charAt(0) == '+') {
					moteurRPN.appliquer(Operation.PLUS);
				} else if(input.charAt(0) == '-') {
					moteurRPN.appliquer(Operation.MOINS);
				} else if(input.charAt(0) == '*') {
					moteurRPN.appliquer(Operation.MULT);
				} else if(input.charAt(0) == '/') {
					moteurRPN.appliquer(Operation.DIV);
				} else {
					operande = Double.parseDouble(input);
					if(operande >= MIN_VALUE && operande <= MAX_VALUE) {
						moteurRPN.enregistrer(operande);
					}
					else
						throw new HorsBornesException("La valeur doit etre entre "+MIN_VALUE+"et "+MAX_VALUE);
				}
				System.out.println(moteurRPN.toString());
			}catch (PileException e) {
				System.out.println(e.getMessage());
			}
			catch (NonDivisibleException e)
			{
				System.out.println(e.getMessage());
			}
			catch(HorsBornesException e)
			{
				System.out.println(e.getMessage());
			}
			input = scan.nextLine();
		}
		scan.close();
	}
}
