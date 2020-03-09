import java.util.*;

public abstract class Cryptogram {

	protected HashMap<String, String> userGuess;
	protected HashMap<String, String> cryptoMapping;
	protected HashMap<String, String> answerMapping;
	protected String phrase;
	protected ArrayList<String> encryptedPhrase;

	public boolean hasMapping(String s) {
		return cryptoMapping.containsKey(s);
	}
	
	public boolean hasUserGuess(String s) { 
	    return userGuess.containsValue(s); 
	}

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


	public void addLetter(String c, String n) {
		userGuess.put(c, n);
	}


	public void undoLetter(String c) {
		userGuess.remove(c);
	}

	public boolean isSolved() {
		if(userGuess.size() < cryptoMapping.size()) {
			return false;
		}
		if(userGuess.equals(answerMapping)) {
			System.out.println("You have solved the cryptogram.");
			return true;
		}
		else{
			System.out.println("Your solution is incorrect.");
			return false;
		}
	}

	public Set<String> getKeyList() {
		return answerMapping.keySet();
	}

}

