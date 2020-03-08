
public class Main {
	
	public static void main(String[] args) {
		
		Game game = new Game("player", "NumberCryptogram");
		LetterCryptogram cryptogram = new LetterCryptogram();
		
		cryptogram.matchLetterToLetter();
		cryptogram.initialPrinting();
		cryptogram.printLetters();
		System.out.println("\n");
		cryptogram.enterLetter('t');	// test for letter t
		System.out.println("\n");
		cryptogram.enterLetter('s');
		System.out.println("\n");
	}
	
	
}
