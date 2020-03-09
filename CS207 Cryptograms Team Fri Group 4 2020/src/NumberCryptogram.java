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
	
	public void matchLetterToNumber() {
		this.cryptoMapping = new HashMap<String, String>();
		this.answerMapping = new HashMap<String, String>();

		HashSet<String> unique = new HashSet<>();
		for (Character c: phrase.toCharArray()) {
			if (Character.isDigit(c) || Character.isLetter(c)) {
				unique.add(c.toString());
			}
		}

		Integer[] integerArray = new Integer[26];
	    for (int i = 0; i < integerArray.length; i++) {
	        integerArray[i] = i + 1;
	    }
	    Collections.shuffle(Arrays.asList(integerArray));

	    int i = 0;
		for (String c: unique) {
			cryptoMapping.put(c, integerArray[i].toString());
			answerMapping.put(integerArray[i].toString(), c);
			i++;
		}
	}

	/*
	public char getPlainLetter(int cryptoValue) {
		
	}
	*/

}
