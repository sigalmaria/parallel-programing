package allocationAlgorithm;

import java.util.LinkedList;

import calculationUtil.CUtil;
import process.Process;
import process.Process.AllocationRequest;
import resources.Resources;

public class ExampleAlgo2 implements Algorithm{

	Resources resources;
	Process[] processes;
	
	LinkedList<Integer> q = new LinkedList<Integer>();

	@Override
	public void init(Resources resources, Process[] processes) {
		this.processes = processes;
		this.resources = resources;
	}

	@Override
	public String nextStep(AllocationRequest[] requests) {
		int[] free = resources.getFreeRes();
		
		if(!q.isEmpty() && processes[q.peek()].isFinished())
			q.remove();
		
		for (AllocationRequest req : requests) {
			int id = req.getProcess().getId();
			
			if(!q.contains(id))
				q.add(id);
			
			if(q.size() >= 1 && id == q.get(0) && CUtil.greaterEqual(free, req.getRequestedRes())){
				free = CUtil.sub(free, req.getRequestedRes());
				req.grant();
			} else if(q.size() >= 2 && id == q.get(1) && CUtil.greaterEqual(free, req.getRequestedRes())){
				free = CUtil.sub(free, req.getRequestedRes());
				req.grant();
			}
		}

		return q.toString() + ", safe state : " + checkIfSafeState();
	}
	
	
	private Boolean checkIfSafeState(){
		int[] free = resources.getFreeRes();
		int counter =0;
		for (int i=1;i<processes.length;i++) {
			
			Process pro = processes[i];
			if( !CUtil.greaterEqual(free, pro.getPendingRes())){
				counter++;
			}

			
		}
		
		return counter==processes.length;
		
		
	}
	
	
	

}
