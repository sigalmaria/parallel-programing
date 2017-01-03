package allocationAlgorithm;

import calculationUtil.CUtil;
import process.Process;
import process.Process.AllocationRequest;
import resources.Resources;

public class BankersAlgo implements Algorithm {

	Resources resources;
	Process[] processes;

	int[][] acquiredRes;
    int[][] pendingRes;
	int[] freeRes;

	@Override
	public void init(Resources resources, Process[] processes) {
		this.resources = resources;
		this.processes = processes;
	}

	@Override
	public String nextStep(AllocationRequest[] requests) {

		freeRes =resources.getFreeRes();
		for(int i=1;i<=processes.length;i++){
			if( processes[i]!=null){
			pendingRes[i] = processes[i].getPendingRes();
			}
			if( processes[i]!=null){
			acquiredRes[i] = processes[i].getAcquiredRes();
			}
		}
		int marks =0;
		int counter=0;
		while(marks<=processes.length){
			
				counter = counter% requests.length;
				AllocationRequest req = requests[counter];
					if(CUtil.greaterEqual(freeRes , req.getRequestedRes())){
						
						freeRes = CUtil.add(acquiredRes[req.getProcess().getId()],freeRes);
						marks =0;
					}else{
						marks++;
					}
				counter++;
					
				
		}
		
		
		return null;
	}

}
