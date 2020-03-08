
public class Main {
	
	public static void main(String[] args) {
		
		Game game = new Game("player", "LetterCryptogram");
		LetterCryptogram cryptogram = new LetterCryptogram();
		
		cryptogram.matchLetterToLetter();
		cryptogram.initialPrinting();
		cryptogram.printLetters();
		System.out.println("\n");
		cryptogram.enterLetter('t');
		System.out.println("\n");
		cryptogram.enterLetter('s');		// Testing the different functions
		System.out.println("\n");
		cryptogram.enterLetter('w');
		System.out.println("\n");
		cryptogram.undoLetter('t');
		System.out.println("\n");
		cryptogram.undoLetter('s');
		
	}
	
	
}
