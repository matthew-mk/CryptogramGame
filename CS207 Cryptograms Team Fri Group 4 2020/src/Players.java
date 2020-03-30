import java.util.ArrayList;
import java.io.File;
import java.util.*;

public class Players {

	public ArrayList<Player> allPlayers;

	public Players(){
		allPlayers = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		try {
			input = new Scanner(new File("PlayerData.txt"));
		}
		catch (Exception e) {
			System.out.println("File could not be found.");
		}
		while(input.hasNext()) {
			for(int i = 0; i < 6; i++){
				input.next();
			}
			Player p  = new Player(input.next());
			addPlayer(p);
		}
	}

	public void addPlayer(Player p) {
		allPlayers.add(p);
	}

	public Player findPlayer(String playerName) {
		if(allPlayers.size() == 0){
			return null;
		}
		for(Player player: allPlayers){
			if(player.getUsername().equals(playerName)){
				return player;
			}
		}
		return null;
	}

	public int getPlayerId(Player p){
		for(int i = 0; i < allPlayers.size(); i++){
			if(p.getUsername().equals(allPlayers.get(i).getUsername())){
				return i;
			}
		}
		return -1;
	}
}
