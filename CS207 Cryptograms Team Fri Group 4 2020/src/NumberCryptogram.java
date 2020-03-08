import java.util.*;

public class NumberCryptogram extends Cryptogram {

	public NumberCryptogram(String file) {
		this.phrase = file;
		this.userGuess = new HashMap<>();
		matchLetterToNumber();

		this.encryptedPhrase = new ArrayList<>();
		for (Character c: phrase.toCharArray()) {
			String out = cryptoMapping.get(c.toString());
			if (out != null) {
				encryptedPhrase.add(out);
			} else {
				encryptedPhrase.add(c.toString());
			}
			encryptedPhrase.add(" ");
		}
	}
	
	/*
	public char getPlainLetter(int cryptoValue) {
		
	}
	*/
	
	public void matchLetterToNumber() {
		this.cryptoMapping= new HashMap<String, String>();
		Integer[] integerArray = new Integer[26];

		HashSet<String> unique = new HashSet<>();
		for (Character c: phrase.toCharArray()) {
			if (Character.isDigit(c) || Character.isLetter(c)) {
				unique.add(c.toString());
			}
		}
		
	    for (int i = 0; i < integerArray.length; i++) {
	        integerArray[i] = i + 1;
	    }
	    Collections.shuffle(Arrays.asList(integerArray));

	    int i = 0;
		for (String c: unique) {
			cryptoMapping.put(c, integerArray[i].toString());
			i++;
		} 
	}


	@Override
	public void addLetter(String c, String n) {
		userGuess.put(c, n);
	}

	@Override
	public void undoLetter(String c) {
		userGuess.remove(c);
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
