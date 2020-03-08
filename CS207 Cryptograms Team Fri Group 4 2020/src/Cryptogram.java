import java.util.*;

public abstract class Cryptogram {

	protected HashMap<Character, Character> userGuess;
	protected HashMap<Character, Character> cryptoMapping;
	protected String phrase;
	
	public abstract void addLetter(Character c, Character n);
	public abstract void undoLetter(Character c);
	public abstract boolean isSolved();

	public abstract void display();

	public void displayPuzzle() {
		System.out.println("The current state is: ");
		for (Character c: cryptoMapping.keySet()) {
			System.out.print(c);
		}

		System.out.println();

		for (Character c: phrase.toCharArray()) {
			Character mapping = userGuess.get(c);
			if (!Character.isLetter(c) && !Character.isDigit(c)) {
				System.out.print(' ');
			} else if (mapping != null) {
				System.out.print(c);
			} else {
				System.out.print('_');
			}
		}
	}
	
}
