import java.util.ArrayList;
import java.io.File;
import java.util.*;

public class Players {

	public ArrayList<Player> allPlayers;
	public File playerFile;

	public Players(){
		allPlayers = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		try {
			input = new Scanner(new File("PlayerData.txt"));
		}
		catch (Exception e) {
			System.out.println("File could not be found.");
		}
		if(input.hasNextLine()) {
			String line = input.nextLine();
			System.out.println(line);
		}
	}


	public void addPlayer(Player p) {
		allPlayers.add(p);
	}

	public void savePlayers() {

	}

	public Player findPlayer(String playerName) {
		if(allPlayers.size() == 0){
			return null;
		}
		for(Player player: allPlayers){
			if(player.getUsername() == playerName){
				return player;
			}
		}
		return null;
	}

	public int getAllPlayersAccuracies() {
		return 0;
	}

	public int getAllPlayersCryptogramsPlayed() {
		return 0;
	}

	public int getAllPlayersCompletedCryptos() {
		return 0;
	}

	public void getAllPlayerNames(){
		System.out.println("List of all players: ");
		for(Player p: allPlayers){
			System.out.println(p.getUsername());
		}
	}

}
