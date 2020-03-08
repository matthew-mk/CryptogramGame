import java.util.*;

public abstract class Cryptogram {

	protected HashMap<String, String> userGuess;
	protected HashMap<String, String> cryptoMapping;
	protected String phrase;
	protected ArrayList<String> encryptedPhrase;
	
	public abstract void addLetter(String c, String n);
	public abstract void undoLetter(String c);
	public abstract boolean isSolved();

	public void displayPuzzle() {
		System.out.println(userGuess);
		System.out.println("The current state is: ");
		for (Character c: phrase.toCharArray()) {
			String out = cryptoMapping.get(c.toString());
			if (out != null) {
				System.out.print(out);
			} else {
				System.out.print(c);
			}
			System.out.print(' ');
		}

		System.out.println();

		for (String c: encryptedPhrase) {
			if (c.equals(" ")) {
				System.out.print(' ');
			} else if (userGuess.containsKey(c)) {
				System.out.print(userGuess.get(c));
			} else {
				System.out.print("_ ");
			}
		}
		System.out.println();
	}
	
}
