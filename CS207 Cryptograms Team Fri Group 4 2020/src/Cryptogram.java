import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cryptogram {
	
	public String phrase = "two birds with the one stone";	// for testing
	public char[] cryptogramAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public Integer[] integerArray;
	public ArrayList<String> cryptogramPhrases;
	public ArrayList<Integer> encryptedMessage;
	public HashMap<Character, Integer> encryptionMapping;
	
	
	public String getPhrase() {
		return phrase;
	}
	
	public int getPhraseLength() {
		return phrase.length();
	}
	
	/*
	public ArrayList<Integer> getFrequencies() {
		
	
	}
	*/
	
	
	public void initialPrinting() {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else {
				System.out.print("_ ");
			}
		}
		System.out.println("\n");
	}
	
	
	public void matchLetterToNumber() {
		encryptionMapping = new HashMap<Character, Integer>();
		Integer[] integerArray = new Integer[26];
		
	    for (int i = 0; i < integerArray.length; i++) {
	        integerArray[i] = i + 1;
	    }
	    Collections.shuffle(Arrays.asList(integerArray));
	    System.out.println();
	    
		for (int i = 0; i < 26; i++) {
			encryptionMapping.put(cryptogramAlphabet[i], integerArray[i]);
		} 
	}
	
	public void printNumbers() {
		for (int i = 0; i < getPhraseLength(); i++) {
			if (phrase.charAt(i) == ' ') {
				System.out.print("   ");
			}
			else { 
				System.out.print(encryptionMapping.get(phrase.charAt(i)) + " ");
			}
			
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
	
}
