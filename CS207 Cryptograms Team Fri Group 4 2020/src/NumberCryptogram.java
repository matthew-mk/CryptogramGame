import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class NumberCryptogram extends Cryptogram {

	public NumberCryptogram(String file) {
		this.phrase = file;
		this.userGuess = new HashMap<>();
		matchLetterToNumber();
	}
	
	/*
	public char getPlainLetter(int cryptoValue) {
		
	}
	*/
	
	public void matchLetterToNumber() {
		this.cryptoMapping= new HashMap<Character, Character>();
		Integer[] integerArray = new Integer[26];

		HashSet<Character> unique = new HashSet<>();
		for (Character c: phrase.toCharArray()) {
			if (Character.isDigit(c) || Character.isLetter(c)) {
				unique.add(c);
			}
		}
		
	    for (int i = 0; i < integerArray.length; i++) {
	        integerArray[i] = i + 1;
	    }
	    Collections.shuffle(Arrays.asList(integerArray));

	    int i = 0;
		for (Character c: unique) {
			cryptoMapping.put(c, integerArray[i]);
		} 
	}
	
	public void enterLetter(char c) {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else if (phrase.charAt(i) == c) {
				System.out.print(c + " ");
			}
			else {
				System.out.print("_ ");
			}
		}
	}

	@Override
	public void addLetter(Character c, Character n) {

	}

	@Override
	public void undoLetter(Character c) {

	}

	@Override
	public boolean isSolved() {
		return false;
	}
	
	
	
	/*
	public void undoLetter(char c) {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else if (phrase.charAt(i) == c) {
				phrase.replace('t', ' ');
				System.out.print("_ ");
				
			}
			else {
				
			}
		}
	}
	*/
	

}
