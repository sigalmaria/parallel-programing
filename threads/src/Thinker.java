

public class Thinker implements Runnable {

	
	

	
	Thread thread;
	
	public Thinker(String name){
		// create a new thread for a player 
		this.thread = new Thread(this,name);
		
	}
	
	@Override
	public void run() {
		
	
	}
	/**
	 * suspends execution of thread for time specified in milisecs 
	 * 
	 * @param mSec a time period to suspend the thread
	 */
	
	public static void think(int mSec){
		
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			
			System.out.println("interupted");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * suspends execution of thread 
	 * for random time in range specified 
	 * 
	 * @param minMillisekunden
	 * @param maxMillisekunden
	 */
	
	public static void randomThink( int minMillisekunden, int maxMillisekunden ){
		
	
		
		int randomMSec =minMillisekunden+ (int)Math.random()*(maxMillisekunden-minMillisekunden+1);
		try {
			Thread.sleep(randomMSec);
		} catch (InterruptedException e) {
			System.out.println("interupted");
			e.printStackTrace();
		}
	}

}
