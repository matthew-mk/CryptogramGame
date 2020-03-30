import java.util.*;

public class LetterCryptogram extends Cryptogram {

	public LetterCryptogram(String file) {
		this.phrase = file;
		this.userGuess = new HashMap<>();
		matchLetterToLetter();

		this.encryptedPhrase = new ArrayList<>();
		for (Character c: phrase.toCharArray()) {
			String out = cryptoMapping.get(c.toString());
			encryptedPhrase.add(Objects.requireNonNullElseGet(out, c::toString));
			encryptedPhrase.add(" ");
		}
	}

	public LetterCryptogram(String phrase, HashMap<String, String> mapping, HashMap<String, String> userGuess) {
		this.phrase = phrase;
		this.userGuess = userGuess;
		this.cryptoMapping = mapping;
		this.answerMapping = new HashMap<>();

		for (Map.Entry<String, String> entry: cryptoMapping.entrySet()) {
			answerMapping.put(entry.getValue(), entry.getKey());
		}

		this.encryptedPhrase = new ArrayList<>();
		for (Character c: phrase.toCharArray()) {
			String out = cryptoMapping.get(c.toString());
			encryptedPhrase.add(Objects.requireNonNullElseGet(out, c::toString));
			encryptedPhrase.add(" ");
		}
	}

	public void matchLetterToLetter() {
		this.cryptoMapping = new HashMap<>();
		this.answerMapping = new HashMap<>();

		HashSet<String> unique = new HashSet<>();
		for (Character c: phrase.toCharArray()) {
			if (Character.isDigit(c) || Character.isLetter(c)) {
				unique.add(c.toString());
			}
		}

		String[] letterArray = new String[26];
		for (char ch = 'a'; ch <= 'z'; ch++)
		{
			letterArray[ch - 'a'] = String.valueOf(ch);
		}
		Collections.shuffle(Arrays.asList(letterArray));

		int i = 0;
		for (String c: unique) {
			cryptoMapping.put(c, letterArray[i]);
			answerMapping.put(letterArray[i], c);
			i++;
		}
	}
}
