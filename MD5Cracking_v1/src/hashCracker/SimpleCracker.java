package hashCracker;


import java.util.ArrayList;
import java.util.Arrays;

import fx.ResultPool;

public class SimpleCracker implements HashCracker{

	ResultPool resPool;
	SimpleCrackerTask task;
	
	

	
	@Override
	public void start(String[] wordList, String[] hashList, ResultPool resPool) {
		System.out.println("Meow");
		
		ArrayList list =new  ArrayList<String>(Arrays.asList(hashList));
		
		this.resPool = resPool;
		 task = new SimpleCrackerTask("cackit",wordList,list, resPool);
		task.th.start();
	
		
	}
	
	@Override
	public void stop() {
		task.th.interrupt();
	}

	

}
