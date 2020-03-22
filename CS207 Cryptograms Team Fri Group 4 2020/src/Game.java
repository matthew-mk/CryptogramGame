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
					if (!promptSave()) {
						break;
					}
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

	public boolean promptSave() {
		saveGame();
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to continue playing this cryptogram? (Y/N)");
		while (true) {
			String yn = input.next();
			if (yn.equals("Y")) {
				return true;
			} else if (yn.equals("N")) {
				return false;
			} else {
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
		System.out.println("Please enter your username:");
		String username = input.next();
		if (playerExists(username)) {
			System.out.println("A player already exists with that username - would you like to load that profile?");
			String yn;
			while (input.hasNext()) {
				yn = input.next();
				if (yn.equals("Y")) {
					currentPlayer = new Player(username);
					break;
				} else if (!yn.equals("N")) {
					System.out.println("Command not understood.");
				} else {
					break;
				}
			}
		} else {
			currentPlayer = new Player(username);
		}
	}

	public boolean playerExists(String username) {
		return false;
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

	public boolean checkSaveExists() {
		boolean exists = false;
		try {
			Scanner input = new Scanner(new FileInputStream("SavedCG.txt"));
			while (input.hasNext()) {
				String line = input.nextLine().trim();
				if (line.equals(currentPlayer.username)) {
					exists = true;
				}
			}
		} catch (FileNotFoundException e) {

		}
		return exists;
	}

	public void saveGame() {
		if (checkSaveExists()) {
			System.out.println("You already have a saved game with this username. Would you like to overwrite it? Y/N");
			Scanner input = new Scanner(System.in);
			while (true) {
				String ans = input.next();
				if (ans.equals("N")) {
					System.out.println("Save aborted.");
					return;
				} else if (!ans.equals("Y")) {
					System.out.println("Command not understood.");
				} else {
					break;
				}
			}
		}

		eraseLastGame();
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new OutputStreamWriter (new FileOutputStream("SavedCG.txt")));

			output.write(currentPlayer.username + "\n");
			output.write(cryptType + "\n");
			output.write(cryptogram.phrase+ "\n");
			output.write(cryptogram.cryptoMapping.size() + "\n");
			for (Map.Entry<String,String> entry: cryptogram.cryptoMapping.entrySet()) {
				output.write(entry.getKey() + " " + entry.getValue() + "\n");
			}

			output.write(cryptogram.userGuess.size() + "\n");
			for (Map.Entry<String,String> entry: cryptogram.userGuess.entrySet()) {
				output.write(entry.getKey() + " " + entry.getValue() + "\n");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void eraseLastGame() {
		BufferedReader reader = null;
		File tmp = new File("tmp.tmp");
		if (!tmp.isFile()) {
			try {
				tmp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File current = new File("SavedCG.txt");

		if (!current.isFile()) {
			try {
				current.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (
				BufferedReader curr = new BufferedReader(new FileReader(current));
				BufferedWriter temp = new BufferedWriter(new FileWriter(tmp, false))) {
			String line;
			while ((line = curr.readLine()) != null) {
				String nonl = line.trim();
				if (nonl.equals(currentPlayer.username)) {
					curr.readLine();
					curr.readLine();
					Integer n = Integer.parseInt(curr.readLine().trim());
					for (int i=0; i<n; i++) {
						curr.readLine();
					}
					Integer m = Integer.parseInt(curr.readLine().trim());
					for (int i=0; i<m; i++) {
						curr.readLine();
					}
				} else {
					temp.write(nonl);
				}
			}
			tmp.renameTo(current);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Returns true if a game exists and was successfully loaded */
	public boolean loadGame() {
		if (!checkSaveExists()) {
			System.out.println("Error: You do not yet have a save file under this name.");
			return true;
		}

		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader((new FileInputStream("SavedCG.txt"))));

			String username;
			while((username = input.readLine()) != null) {
				username = username.trim();
				if (username.equals(currentPlayer.username)) {
					String type = input.readLine().trim();
					String phrase = input.readLine().trim();

					int elems = Integer.parseInt(input.readLine().trim());
					HashMap<String, String> cryptMapping = new HashMap<>();
					for (int i = 0; i < elems; i++) {
						String[] kv = input.readLine().trim().split(" ");
						String key = kv[0];
						String val = kv[1];
						cryptMapping.put(key, val);
					}

					int nguess = Integer.parseInt(input.readLine().trim());
					HashMap<String, String> userGuess = new HashMap<>();
					for (int i = 0; i < nguess; i++) {
						String[] kv = input.readLine().trim().split(" ");
						String key = kv[0];
						String val = kv[1];
						userGuess.put(key, val);
					}

					Cryptogram loaded;
					if (type.equals("NumberCryptogram")) {
						loaded = new NumberCryptogram(phrase, cryptMapping, userGuess);
					} else {
						loaded = new LetterCryptogram(phrase, cryptMapping, userGuess);
					}
					this.cryptogram = loaded;
					return true;
				} else {
					input.readLine();
					input.readLine();

					int elems = Integer.parseInt(input.readLine().trim());
					for (int i = 0; i < elems; i++) {
						input.readLine();
					}

					int nguess = Integer.parseInt(input.readLine().trim());
					for (int i = 0; i < nguess; i++) {
						input.readLine();
					}
					input.readLine();
				}
			}
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void showSolution() {
		
	}
	
	
}

