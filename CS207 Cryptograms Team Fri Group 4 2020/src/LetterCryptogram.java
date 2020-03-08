import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class LetterCryptogram extends Cryptogram {
	
	public void Cryptogram(String file) {
		
	}
	
	public void Cryptogram() {
		
	}
	
	/*
	public char getPlainLetter(char cryptoLetter) {
		
	}
	*/
	
	public void printLetters() {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else { 
				System.out.print(letEncryptionMapping.get(phrase.charAt(i)) + " ");
			}
			
		}
	}
	
	public void matchLetterToLetter() {
		letEncryptionMapping = new HashMap<Character, Character>();
		visibleLetters = new HashMap<Character, Boolean>();
		Character[] charArray = new Character[26];
		
	    for (int i = 0; i < charArray.length; i++) {
	        charArray[i] = cryptogramAlphabetUpperCase[i];
	    }
	    Collections.shuffle(Arrays.asList(charArray));
	    System.out.println();
	    
		for (int i = 0; i < 26; i++) {
			letEncryptionMapping.put(cryptogramAlphabet[i], charArray[i]);
			visibleLetters.put(cryptogramAlphabet[i], false);
		} 
	}
	
	public void enterLetter(char c) {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else if (phrase.charAt(i) == c) {
				visibleLetters.put(phrase.charAt(i), true);
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
