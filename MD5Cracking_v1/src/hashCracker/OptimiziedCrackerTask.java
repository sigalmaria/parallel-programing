package hashCracker;






import fx.ResultPool;
import java.util.List;

public class OptimiziedCrackerTask implements Runnable {
	    public Thread th;
	    private String[] wordList;
		private List<String> hashList;
		ResultPool resPool;
		private int start;
		private int end;
		public static boolean 	stopThreads=false;
		
		
	
	public  OptimiziedCrackerTask(String name, 
			String[] wordList,
			List<String> hashList,
			ResultPool resPool,
			int start,
			int end) {
		     th = new Thread(this,name);
			 this.wordList = wordList;
			 this.hashList = hashList;
			 this.resPool = resPool;
			 this.start = start;
			 this.end = end;
	}
	@Override
	public void run() {
		
		for(int i=start;i< end;i++){
			if (stopThreads) break;
		
			String firstWord =wordList[i];
			System.out.println("Running: " + th.getName() +' ' + firstWord);
			String secondWord;
			for(int j=0;j< wordList.length;j++){
				secondWord = wordList[j];
				String password = firstWord+secondWord;
				String hashWert=Utils.MD5(password);
				if(hashList.contains(hashWert)){
					resPool.pushPassword(password);
					hashList.remove(hashWert);
					if(hashList.size()==0){
						stopThreads=true;
						
					}
					
					
				}
				
			}
			
			
			
		}
		
	}

}
