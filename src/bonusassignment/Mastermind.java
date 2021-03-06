/* Bryce Morgan, George Tang
 * bcm2355, gdt382 
 * EE422C- Bonus Assignment
 */

package bonusassignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Mastermind {

	static ArrayList<Character> code = new ArrayList<Character>();

	public static void main(String args[]) {
		setCode();
		boolean done = false;
		System.out.println(
				"***Welcome to Mastermind!*** \nThere is a secret code consisting of 4 pegs of different colors. \nInput a string of 4 characters based on the colors you guess:"
						+ "\n\t'B'lue, 'G'reen, 'O'range, 'P'urple, 'R'ed, or 'Y'ellow. \nA black peg indicates one of the pegs in your guess is"
						+ "\nboth the correct color and position, while a white"
						+ "\npeg indicates one of the pegs is the correct color " + "\nbut in the wrong position."
						+ " \nIf you manage to guess the secret code" + "\nin 12 guesses or less, you win!");
		// System.out.println("" + code.get(0) + "" + code.get(1) + "" +
		// code.get(2) + "" + code.get(3));
		StopWatch stopWAtch = new StopWatch();
		int guesses = 0;
		for (int i = 0; i < 12 && !done; i++) {
			System.out.println("\nGuesses remaining: " + (12 - i));
			done = guess();
			if (i == 0) {
				stopWAtch.start();
			}
			guesses = i + 1;
		}

		if (done) {
			stopWAtch.stop();
			System.out.println("***Congratulations, you win!***");
			System.out
					.println("***You took " + stopWAtch.getElapsedTime() + " milliseconds. Try to beat this time!***");
			System.out.println("***You took " + guesses + " guesses. Can your friends beat this?***");
		} else {
			System.out.println("Out of guesses! Maybe next time.");
			System.out.println(
					"The correct answer is: " + code.get(0) + "" + code.get(1) + "" + code.get(2) + "" + code.get(3));
		}
	}

	public static void setCode() {
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			int peg = rand.nextInt(6);
			if (peg == 0) {
				code.add(i, 'B');
			}
			if (peg == 1) {
				code.add(i, 'G');
			}
			if (peg == 2) {
				code.add(i, 'O');
			}
			if (peg == 3) {
				code.add(i, 'P');
			}
			if (peg == 4) {
				code.add(i, 'R');
			}
			if (peg == 5) {
				code.add(i, 'Y');
			}
		}
	}

	public static boolean guess() {
		boolean complete = false;
		boolean correct = false;
		while (!complete) {
			try {
				int blackPegs = 0;
				int whitePegs = 0;
				ArrayList<Character> codeTemp = (ArrayList<Character>) code.clone();
				System.out.print("Input 4 of the following characters: B, G, O, P, R, Y\n>");
				BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
				String input = read.readLine();
				if (input.length() != 4) {
					System.out.println("Invalid input");
					continue;
				}

				boolean invalid_input = false;
				for (int i = 0; i < 4; i++) {
					char color = input.charAt(i);
					if (!(color == 'B' || color == 'G' || color == 'O' || color == 'P' || color == 'R'
							|| color == 'Y')) {
						System.out.println("Invalid input");
						invalid_input = true;
						break;
					}
				}
				if (invalid_input) {
					continue;
				}

				for (int i = 0; i < 4; i++) {
					char color = input.charAt(i);
					if (color == code.get(i)) {
						blackPegs++;
						codeTemp.remove((Character) color);
					}
				}
				for (int i = 0; i < 4; i++) {
					char color = input.charAt(i);
					if (color == code.get(i))
						continue;
					if (codeTemp.contains((Character) color)) {
						whitePegs++;
						codeTemp.remove((Character) color);
					}
				}
				System.out.println("Black pegs: " + blackPegs + "\nWhite pegs: " + whitePegs);
				if (blackPegs == 4)
					correct = true;
				complete = true;
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		}
		return correct;
	}
	
}
