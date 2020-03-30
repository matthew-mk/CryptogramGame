import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

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

		System.out.println("Enter your username: ");
		String username = input.next();
		Game game = new Game(username, cType);
		game.run();
	}
}
