import java.nio.file.Files;
import java.util.*;
import java.io.*;

public class Game {

	public ArrayList<String> phrases;
	public int phrasesCounter = 0;
	public HashMap<Player, Game> playerGameMapping;
	public Cryptogram cryptogram;
	public Players allPlayers = new Players();
	public Player currentPlayer;
	public String cryptType;
	public boolean quit = false;
	public Formatter formatter;

	public Game(String p, String cryptType) {
		if(allPlayers.findPlayer(p) == null) {
			currentPlayer = new Player(p);
			allPlayers.addPlayer(currentPlayer);
		}
		else{
			currentPlayer = allPlayers.findPlayer(p);
		}
		this.cryptType = cryptType;
	}

	public Game(String p) {
		if(allPlayers.findPlayer(p) == null) {
			currentPlayer = new Player(p);
			allPlayers.addPlayer(currentPlayer);
		}
		else{
			currentPlayer = allPlayers.findPlayer(p);
		}
		cryptType = cryptTypeDecider();
	}

	public void run() throws IOException {
		Scanner input = new Scanner(System.in);
		loadData();
		while (!quit) {
			generateCryptogram(cryptType);
			while (!cryptogram.isSolved() && !quit) {
				System.out.printf("Cryptograms Completed: %d\nCryptograms Played: %d\nAccuracy: %d\n", currentPlayer.getNumCryptogramsCompleted(), currentPlayer.getNumCryptogramsPlayed(), currentPlayer.getAccuracy());
				cryptogram.displayPuzzle();
				System.out.print("\nWould you like to add or remove a character? ");

				String ans = input.next();
				if (ans.equals("add")) {
					enterLetter();
				} else if (ans.equals("remove")) {
					undoLetter();
				}
				else {
					System.out.println("Command not understood.");
				}
			}
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
				quit = true;
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

	public void getHint() {

	}

	public void loadPlayer() {

	}

	public void playGame() {

	}

	public void generateCryptogram(String cryptoType) {
		phrases = new ArrayList<>();
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
		String currentPhrase = phrases.get(phrasesCounter);

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

	public void saveData() throws IOException {
		File file = new File("PlayerData.txt");
		List<String> data = Files.readAllLines(file.toPath());
		String line = new String(String.valueOf(currentPlayer.getNumCryptogramsCompleted() + " ") +
								String.valueOf(currentPlayer.getNumCryptogramsPlayed() + " ") +
								String.valueOf(currentPlayer.getAccurateGuesses() + " ") +
								String.valueOf(currentPlayer.getTotalGuesses() + " ") +
								String.valueOf(currentPlayer.getAccuracy() + " ") +
								currentPlayer.getUsername());
		int pos = allPlayers.getPlayerId(currentPlayer);
		if(pos == data.size()){
			data.add(line);
		}
		else {
			data.set(pos, line);
		}

		FileWriter writer = new FileWriter("PlayerData.txt");
		BufferedWriter bwriter = new BufferedWriter(writer);
		for(String l: data) {
			bwriter.write(l);
			bwriter.newLine();
		}

		bwriter.close();
		writer.close();

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
			int cryptogramsCompleted = input.nextInt();
			int cryptogramsPlayed = input.nextInt();
			int accurateGuesses = input.nextInt();
			int totalGuesses = input.nextInt();
			int accuracy = input.nextInt();
			String username = input.next();
			if(username.equals(currentPlayer.getUsername())){
				currentPlayer.cryptogramsCompleted = cryptogramsCompleted;
				currentPlayer.cryptogramsPlayed = cryptogramsPlayed;
				currentPlayer.accurateGuesses = accurateGuesses;
				currentPlayer.totalGuesses = totalGuesses;
				currentPlayer.accuracy = accuracy;
				break;
			}

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
