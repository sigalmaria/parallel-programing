package hashCracker;

import java.util.ArrayList;

import fx.ResultPool;

public class SimpleCrackerTask implements Runnable {

    public Thread th;
    private String[] wordList;
	private ArrayList<String> hashList;
	ResultPool resPool;

	public SimpleCrackerTask(String name, 
			String[] wordList,
			ArrayList<String> hashList, 
			ResultPool resPool){
		
	  th = new Thread(this,name);
	 this.wordList = wordList;
	 this.hashList = hashList;
	 this.resPool = resPool;
	 
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<5000;i++){
			String firstWord =wordList[i];
			String secondWord;
			for(int j=0;j<20000;j++){
				secondWord = wordList[j];
				String password = firstWord+secondWord;
				String hashWert=Utils.MD5(password);
				if(hashList.contains(hashWert)){
					resPool.pushPassword(password);
					hashList.remove(hashWert);
					
				}
				
			}
			
			
			
		}
		
		resPool.abort();
	}

	



}
