import java.util.*;

public class Main {
	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to play a \"Number\" or a \"Letter\" cryptogram?");
		String cType;
		while(true){
			cType = input.next();
			if (cType.equals("Number")){
				cType = "NumberCryptogram";
				break;
			}
			else if (cType.equals("Letter")){
				cType = "LetterCryptogram";
				break;
			}
			else{
				System.out.println("You have entered an invalid type.");
			}
		}

		Game game = new Game("player", cType);
		game.run();
	}
	
	
}
