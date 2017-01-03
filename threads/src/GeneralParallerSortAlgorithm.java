import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneralParallerSortAlgorithm<Value extends Comparable<Value>>{

	
	public enum SortAlgorithm{
		SELECTION_SORT,
		INSERTION_SORT,
		BUBLLE_SORT
	}
	private Value[] array;
	private int k;
	public GeneralParallerSortAlgorithm(int k , Value[] arr){
		this.k = k;
		this.array = arr;
		
		
	}
	public static void main(String[] args) {
		Integer[] arr = {0,-1,10,8,99,-6,3,89,16,77,202,1};
		GeneralParallerSortAlgorithm<Integer> sorAlg = new GeneralParallerSortAlgorithm<Integer>(3,arr);
		arr = sorAlg.sort(SortAlgorithm.SELECTION_SORT);
		System.out.println("Sorted Array " + Arrays.toString(arr));
	}
	public Value[] sort( SortAlgorithm alg){
		ExecutorService ser = Executors.newFixedThreadPool(k);
		int steps = array.length/k;
		int pre =0;
		for(int i=0;i<array.length;i+=steps){
		    pre = i;
			ser.submit(new Sort(array, i,i+steps, alg));
		}
		if(pre<array.length){
			ser.submit(new Sort(array, pre,array.length, alg));
			
		}
		ser.shutdown();
		try {
			ser.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		ser = Executors.newFixedThreadPool(k);
		for(int i=0;i+2*steps<array.length;i+=steps){
		    pre = i;
			ser.submit(new Merge(array, i,i+2*steps, i+steps));
		}
	
		try {
			ser.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
		return array;
	}
	

	
private class Merge implements Runnable{
	private Value[] a;
	private int start;
	private int end;
	private int pivot;
	public Merge(Value[]arr , int s, int e, int pivot){
		this.a = arr;
		this.start = s;
		this.end = e;
		this.pivot = pivot;
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int k = start;
		int j = pivot;
		for(int i=start;i<end;i++){
			if(a[k].compareTo(a[j])==1){
				Value temp = a[k];
				a[k] = a[j];
				a[k] = temp;
				j++;
				
			}
			
			
		}
	}
	
	
	
}
	
private  class Sort implements  Runnable {
		
		private Value[] a;
		private int start;
		private int end;
		private SortAlgorithm algo;
		
		public Sort(Value[] arr, 
				int s , 
				int e,
				SortAlgorithm alg){
			this.a = arr;
			this.start = s;
			this.end = e;
			this.algo = alg;
		}
		
		public void run() {
			switch (algo) {
			case INSERTION_SORT:
				insertionSort();
				break;
			case SELECTION_SORT:
				break;
			case BUBLLE_SORT:
				
				break;

			default:
				break;
			}
		}
		
		private void insertionSort(){
			for (int i=start; i<end;i++){
				Value x =a[i];
				int j = i-1;
				while (j>=0 && a[j].compareTo(x)==1){
					a[j+1]=a[j];
					j=j-1;
					
				}
				a[j+1] =x;
			}
		}
	}
	
	
	

}
