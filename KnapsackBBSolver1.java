

// Branch-and-Bound solver
public class KnapsackBBSolver1 extends KnapsackBFSolver
{    protected UPPER_BOUND ub1;
     public int total_value=0;
     public int best_sol=0;
     
     public KnapsackBBSolver1(UPPER_BOUND ub_)
 	{
 		ub1 = ub_;
 	}
 	/********************************************************************/
	public void dispose()
	{

		super.dispose();
	}
	/********************************************************************/
	

	public final void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		bestSoln = soln_;
		crntSoln = new KnapsackSolution(inst);
		for(int i=0;i<=inst.GetItemCnt();i++){
			
			total_value += inst.GetItemValue(i);
		}
		FindSolns(1,0,0);
	}
	public void FindSolns(int itemNum, int weight, int dont_value)
	{

		int itemCnt = inst.GetItemCnt();
		
		
		if(weight > inst.GetCapacity()){
			return;
		}

		if (itemNum == itemCnt + 1){
			CheckCrntSoln();
			return;
			
		}
		int remaining_value=0;
	    remaining_value=total_value-dont_value;
		
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1,weight,(dont_value+inst.GetItemValue(itemNum)));
		
		if(weight <= inst.GetCapacity() && (remaining_value >= bestSoln.GetValue()))
		{
			crntSoln.TakeItem(itemNum);
		    FindSolns(itemNum + 1,(weight+inst.GetItemWeight(itemNum)),dont_value);
			
		}
	
		
	}
	
		

}

		



/*****************************************************************************/