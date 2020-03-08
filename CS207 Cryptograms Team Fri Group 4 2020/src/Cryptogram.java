import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cryptogram {
	
	public String phrase = "two birds with the one stone";	// for testing
	public char[] cryptogramAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public Integer[] integerArray;
	public Character[] charArray;
	public ArrayList<String> cryptogramPhrases;
	public ArrayList<Integer> encryptedMessage;
	public HashMap<Character, Integer> numEncryptionMapping;
	public HashMap<Character, Character> letEncryptionMapping;
	public HashMap<Character, Boolean> visibleLetters;
	
	
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
	
	
	
	
}
