package poo651;

import java.io.*;

public enum Operation {
	PLUS('+') {
		@Override
		public double eval(double a, double b) {
			return a + b;
		}
	},
	MULT('*') {
		@Override
		public double eval(double a, double b) {
			return a * b;
		}
	},
	MOINS('-') {
		@Override
		public double eval(double a, double b) {
			return a - b;
		}
	},
	DIV('/') {
		@Override
		public double eval(double a, double b) {
			double c;
			try {
				c = a / b;
			} catch (ArithmeticException e) {

			}
			return a / b;

		}
	};

	private char symbole;

	Operation(char s) {
		symbole = s;
	}

	public abstract double eval(double a, double b);

}
