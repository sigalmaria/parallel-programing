
public class Game {

	//the number of player participating in game 
	public static int PLAYERS_NUM=10;
	public static void main(String[] args) {
		
		System.out.println("Game starts");
		for( int i=1;i<=PLAYERS_NUM;i++){
			
			// here we create a thread for a player 
			Thinker th = new Player("Player " +i );
			//staring a thread
			th.thread.start();
			
			
		}

	}

}
