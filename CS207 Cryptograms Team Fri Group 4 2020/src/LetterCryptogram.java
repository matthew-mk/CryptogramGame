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


	@Override
	public void addLetter(String c, String n) {

	}

	@Override
	public void undoLetter(String c) {

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
