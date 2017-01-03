import java.util.Arrays;

public class ParallerSortAlgorithm {
	public static void main(String[] args) {
		
		int[] arr ={5,10,5,8,15,20,55,0,7,-16,88,120}; 	
		System.out.println("Array to sort : ");
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println("Sorted array:");
		System.out.println(Arrays.toString(arr));
		
	}
	
	
	
	public static int[]  sort(int[] array){
		
		//staring two threads for two parts of array 
		Thread p1= new Thread(new InsertionSort(array,0,array.length/2));
		p1.start();
		Thread p2= new Thread(new InsertionSort(array,array.length/2+1,array.length));
		p2.start();
		
		try {
			//wait until 2 threads finish sorting part of the array
			p1.join();
			p2.join();
		} catch (InterruptedException e) {
			System.out.println("thread interupted");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//merge two parts
		return merge(array);
		
		
	}
	//combines two pars of array in ascending order 
	private static int[] merge(int[] array){
		int middle = array.length/2;
		for(int i=0;i< middle;i++){
			if(array[i]>array[middle+i]){
				
				int temp = array[i];
				array[i] = array[middle+i];
				array[middle+i] =temp;
			}
			
			
		}
		return array;
		
	}
	
	private static class InsertionSort implements  Runnable {
		
		private int[] a;
		private int start;
		private int end;
		
		public InsertionSort(int[] arr, int s , int e){
			this.a = arr;
			this.start = s;
			this.end = e;
		}
		
		public void run() {
			for (int i=start; i<end;i++){
				int x =a[i];
				int j = i-1;
				while (j>=0 && a[j]>x){
					a[j+1]=a[j];
					j=j-1;
					
				}
				a[j+1] =x;
			}
		}
	}

	
}
