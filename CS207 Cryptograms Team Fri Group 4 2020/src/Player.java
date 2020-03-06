
public class Player {
	
	public String username;
	public int accuracy;
	public int totalGuesses;
	public int cryptogramsPlayed;
	public int cryptogramsCompleted;
	
	
	public Player(String username) {
		this.username = username;
	}
	
	
	public void updateAccuracy() {
		
	}
	
	public void incrementCryptogramsCompleted() {
		cryptogramsCompleted++;
	}
	
	public void incrementCryptogramsPlayed() {
		cryptogramsPlayed++;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public int getNumCryptogramsCompleted() {
		return cryptogramsCompleted;
	}
	
	public int getNumCryptogramsPlayed() {
		return cryptogramsPlayed;
	}

}
