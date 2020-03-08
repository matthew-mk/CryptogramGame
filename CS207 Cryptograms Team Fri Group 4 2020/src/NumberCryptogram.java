import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class NumberCryptogram extends Cryptogram {
	
	
	public void Cryptogram(String file) {
		
	}
	
	public void Cryptogram() {
		
	}
	
	/*
	public char getPlainLetter(int cryptoValue) {
		
	}
	*/
	
	public void printNumbers() {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else if (phrase.charAt(i) == '.') {
				System.out.print(".");
			}
			else { 
				System.out.print(numEncryptionMapping.get(phrase.charAt(i)) + " ");
			}
			
		}
	}
	
	public void matchLetterToNumber() {
		numEncryptionMapping = new HashMap<Character, Integer>();
		visibleLetters = new HashMap<Character, Boolean>();
		Integer[] integerArray = new Integer[26];
		
	    for (int i = 0; i < integerArray.length; i++) {
	        integerArray[i] = i + 1;
	    }
	    Collections.shuffle(Arrays.asList(integerArray));
	    System.out.println();
	    
		for (int i = 0; i < 26; i++) {
			numEncryptionMapping.put(cryptogramAlphabet[i], integerArray[i]);
			visibleLetters.put(cryptogramAlphabet[i], false);
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
			else if (visibleLetters.get(phrase.charAt(i)) == true) {
				System.out.print(phrase.charAt(i) + " ");
			}
			else {
				System.out.print("_ ");
			}
		}
	}

	public void undoLetter(char c) {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else if (phrase.charAt(i) == c) {
				phrase.replace('t', ' ');
				System.out.print("_ ");
			}
			else if (visibleLetters.get(phrase.charAt(i)) == true) {
				System.out.print(phrase.charAt(i) + " ");
			}
			else {
				System.out.print("_ ");
			}
		}
	}
	
	

	
		

}
