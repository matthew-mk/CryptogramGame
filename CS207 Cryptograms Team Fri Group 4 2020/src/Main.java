
public class Main {
	
	public static void main(String[] args) {
		
		Game game = new Game("player", "NumberCryptogram");
		Cryptogram cryptogram = new Cryptogram();
		
		cryptogram.matchLetterToNumber();
		cryptogram.initialPrinting();
		cryptogram.printNumbers();
		System.out.println("\n");
		cryptogram.enterLetter('t');
	}
	
	
}
