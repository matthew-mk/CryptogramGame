import java.util.*;
import java.io.*;

public class Game {
	
	public ArrayList<String> phrases;
	public int phrasesCounter = 0;
	public HashMap<Player, Game> playerGameMapping;
	public Cryptogram cryptogram;
	public Player currentPlayer;
	public String cryptType;
	public Formatter formatter;

	public Game(String p, String cryptType) {
		currentPlayer = new Player(p);
		this.cryptType = cryptType;
		loadPhrases();
	}
	
	public Game(String p) {
		currentPlayer = new Player(p);
		cryptType = cryptTypeDecider();
		loadPhrases();
	}

	public void run() {
		promptDataLoading();
		Scanner input = new Scanner(System.in);
		while (true) {
			generateCryptogram(cryptType);
			while (!cryptogram.isSolved()) {
				printPlayerStats();
				cryptogram.displayPuzzle();
				System.out.print("You may 'add' a character, 'remove' a character, 'save' this cryptogram or 'load' another one\n>");
				String ans = input.next();
				if (ans.equals("add")) {
					enterLetter();
				} else if (ans.equals("remove")) {
					undoLetter();
				} else if (ans.equals("save")) {
					saveGame();
				} else if (ans.equals("load")) {
					loadGame();
				} else {
					System.out.println("Command not understood.");
				}
			}
			System.out.println("YOU WIN!");

			currentPlayer.incrementCryptogramsCompleted();
			currentPlayer.incrementCryptogramsPlayed();
			saveData();
			System.out.println("Would you like to play another cryptogram? Please type 'Yes' or 'No' ");
			String yesNo = input.next();
			if (yesNo.equals("Yes")) {
				phrasesCounter += 1;
				generateCryptogram(cryptType);
			}
			else if (yesNo.equals("No")) {
				System.out.println("\nThe application has been closed.");
				break;
			}
			else {
				System.out.println("Command not understood.");
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

	public void printPlayerStats() {
		System.out.printf("Cryptograms Completed: %d\nCryptograms Played: %d\nAccuracy: %d\n",
				currentPlayer.getNumCryptogramsCompleted(),
				currentPlayer.getNumCryptogramsPlayed(),
				currentPlayer.getAccuracy());
	}
	
	public void getHint() {
		
	}
	
	public void loadPlayer() {
		
	}
	
	public void playGame() {
		
	}

	/* This function should ask the user if they have a saved account that they want to
	load, or if they want to start a new record. In the latter case it needs to use their name
	to create a new player record
	 */
	public void promptDataLoading() {
		Scanner input = new Scanner(System.in);

	}

	public void loadPhrases() {
		phrases = new ArrayList<>();
		File file = new File("Crypto-Phrases.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()){
				phrases.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("Error: the Crypto-Phrases file was not found.");
		}
	}
	
	public void generateCryptogram(String cryptoType) {
		String currentPhrase = phrases.get(0);
		if (cryptoType.equals("NumberCryptogram")) {
			cryptogram = new NumberCryptogram(currentPhrase);
		} 
		else if (cryptoType.equals("LetterCryptogram")) {
			cryptogram = new LetterCryptogram(currentPhrase);
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
		
		if (sym.equals(cryptogram.cryptoMapping.get(x))) {
			currentPlayer.accurateGuesses++;
			currentPlayer.totalGuesses++;
			currentPlayer.updateAccuracy();
		}
		else {
			currentPlayer.totalGuesses++;
			currentPlayer.updateAccuracy();
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
	
	public void saveData() {
		try {
			formatter = new Formatter("PlayerData.txt");
		}
		catch (Exception e) {
			System.out.println("Error: Data could not be saved.");
		}
		
		formatter.format("%d %d %d %d %d", currentPlayer.getNumCryptogramsCompleted(), currentPlayer.getNumCryptogramsPlayed(), currentPlayer.getAccurateGuesses(), currentPlayer.getTotalGuesses(), currentPlayer.getAccuracy());
		formatter.close();
	}
	
	public void loadData() {
		Scanner input = new Scanner(System.in);
		
		try {
			input = new Scanner(new File("PlayerData.txt"));
		}
		catch (Exception e) {
			System.out.println("File could not be found.");
		}
		
		while (input.hasNext()) {
			currentPlayer.cryptogramsCompleted = input.nextInt();
			currentPlayer.cryptogramsPlayed = input.nextInt();
			currentPlayer.accurateGuesses = input.nextInt();
			currentPlayer.totalGuesses = input.nextInt();
			currentPlayer.accuracy = input.nextInt();
		}
		
		input.close();
	}
	
	public void saveGame() {
		
	}
	
	public void loadGame() {
		
	}
	
	public void showSolution() {
		
	}
	
	
}

