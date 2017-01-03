
public class Player extends Thinker {
	//the amount of maximal gain
	public static int WIN_CASH=20;
	private int cash=10;
	public Player(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		// run thread a long the player has credit or play has'nt won maximal 
		//amount of gain 
		while(  !(cash==0 || cash==WIN_CASH) && Game.PLAYERS_NUM>0 ){
			if(Math.random()<0.5){
				//won
					cash++;
					System.out.println(thread.getName() + " won 1 euro. He has currently " + cash );
					
				}else{
					//lost
					cash--;
					System.out.println(thread.getName() + " lost 1 euro. He has currently " + cash );
				}
			//hang the thread execution for random time
			Thinker.randomThink(1000,5000);
		}
		//if player won maximal gain or lost all money then he 
		//is out of the game
		Game.PLAYERS_NUM--;
		if(Game.PLAYERS_NUM <=0){
			
			System.out.println("Game Over");
		}
	}

}
