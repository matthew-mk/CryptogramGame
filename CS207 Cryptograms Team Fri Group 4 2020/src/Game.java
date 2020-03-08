import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	public HashMap<Player, Game> playerGameMapping;
	public Cryptogram cryptogram;
	public Player currentPlayer;
	public String cryptType;
	

	public Game(String p, String cryptType) {
		currentPlayer = new Player(p);
		this.cryptType = cryptType;
	}
	
	public Game(String p) {
		currentPlayer = new Player(p);
		cryptType = cryptTypeDecider();
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		while (true) {
			generateCryptogram(cryptType);
			while (!cryptogram.isSolved()) {
				cryptogram.displayPuzzle();
				System.out.print("Please enter the symbol you want to solve: ");
				String sym = input.next();
				System.out.print("\nPlease enter the mapping for that symbol: ");
				String x = input.next();
				cryptogram.addLetter(sym, x);
			}
		}
	}

	public String cryptTypeDecider() {
		Random randomInt = new Random();
		
		if (randomInt.nextInt(2) == 1) {
			return "LetterCryptogram";
		} else {
			return "NumberCryptogram";
		}
	}
	
	public void getHint() {
		
	}
	
	public void loadPlayer() {
		
	}
	
	public void playGame() {
		
	}
	
	public void generateCryptogram(String cryptoType) {
		if (cryptoType.equals("NumberCryptogram")) {
			cryptogram = new NumberCryptogram("Hello world");
		} 
		else if (cryptoType.equals("LetterCryptogram")) {
			cryptogram = new LetterCryptogram();
		}
	}
	
	public void enterLetter() {
		
	}
	
	public void undoLetter() {
		
	}
	
	public void viewFrequencies() {
		
	}
	
	public void saveGame() {
		
	}
	
	public void loadGame() {
		
	}
	
	public void showSolution() {
		
	}
	
	
}
