package hashCracker;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

import fx.ResultPool;
import java.util.List;
public class OptimiziedCracker implements HashCracker {


	private final int MAX_THREADS=10;
	ResultPool resPool;
	private int wordListSize;
	private int hashListSize;
	
	@Override
	public void start(String[] wordList, String[] hashArray, ResultPool resPool) {
	
		
	    List<String> hashList =  Collections.synchronizedList(new ArrayList<String>(Arrays.asList(hashArray)));
		wordListSize =wordList.length;
		List<Thread> threads = new ArrayList<>();
		
	
		this.resPool = resPool;
		int  threadsNum;
		if(wordListSize<1000){
			threadsNum=1;
		}else if(wordListSize>=1000 && wordListSize<10000){
			threadsNum=5;
			
		}else if(wordListSize>=10000 && wordListSize<20000){
			threadsNum=10;
		}else{
			threadsNum=15;
		}
	    int k = wordListSize/threadsNum;
		//ExecutorService ser = Executors.newFixedThreadPool( MAX_THREADS);
	    int i=0;
	    int j=k;
	  
		for(i=0 ;j<wordListSize;i+=k,j+=k){
			
			OptimiziedCrackerTask crack = new OptimiziedCrackerTask("crack::"+i, wordList, hashList, resPool, i, j);
			threads.add(crack.th);
			crack.th.start();
			
		}
	
			if(j>wordListSize){
				
				OptimiziedCrackerTask crack = new OptimiziedCrackerTask("crack::"+i, wordList, hashList, resPool, i,  wordListSize);
			    crack.th.start();
			}
		

	}

	@Override
	public void stop() {
		OptimiziedCrackerTask.stopThreads=true;
	}

}
