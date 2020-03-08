import java.util.*;
import java.io.*;

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
				System.out.print("Would you like to add or remove a character? ");

				String ans = input.next();
				if (ans.equals("add")) {
					enterLetter();
				} else if (ans.equals("remove")) {
					undoLetter();
				} else {
					System.out.println("Command not understood.");
				}
			}
			break;
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
		ArrayList<String> phrases = new ArrayList<>();
		File file = new File("Crypto-Phrases.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()){
				phrases.add(reader.nextLine());
			}
			reader.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e);
		}
		Random randomInt = new Random();
		int pId;
		pId = randomInt.nextInt(phrases.size() - 1);

		if (cryptoType.equals("NumberCryptogram")) {
			cryptogram = new NumberCryptogram(phrases.get(pId));
		} 
		else if (cryptoType.equals("LetterCryptogram")) {
			cryptogram = new LetterCryptogram(phrases.get(pId));
		}
	}

	public void enterLetter() {
		Scanner input = new Scanner(System.in);
		System.out.print("Which character would you like to map? ");
		String sym = input.next();
		System.out.print("\nPlease enter the mapping for that symbol: ");
		String x = input.next();

		if (cryptogram.hasUserGuess(x)) {
			System.out.println("That character is already mapped.");
		}
		else if ((!cryptogram.getKeyList().contains(sym))){
			System.out.println("That number is not in the phrase.");
		}
		else {
			cryptogram.addLetter(sym, x);
		}
		//input.close();
	}


	public void undoLetter() {
		Scanner input = new Scanner(System.in);
		System.out.print("Which character would you like to remove?");
		String sym = input.next();
		if (!cryptogram.hasMapping(sym)) {
			cryptogram.undoLetter(sym);
		} else {
			System.out.print("That character is not mapped.");
		}
		//input.close();
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
