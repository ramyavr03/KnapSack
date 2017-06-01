

// Backtracking solver
public class KnapsackBTSolver extends KnapsackBFSolver
{
    /*****************************************************************************/

	public KnapsackBTSolver()
	{
		crntSoln = null;
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
		FindSolns(1,0);
		

	}
	public void FindSolns(int itemNum, int weight){

		int itemCnt = inst.GetItemCnt();
		
		if(weight > inst.GetCapacity()){
			return;
		}

		if (itemNum == itemCnt + 1)
		{
			
			/*if(inst.GetItemWeight(itemNum) <= inst.GetCapacity()){*/
			//System.out.println("before checkcrntsol ");
			CheckCrntSoln();
			//System.out.println("after checkcrntsol");
			return;
			//}
		
		}
		
	
		
		//System.out.println();
		//System.out.println("don takeitemno "+itemNum);
		
		crntSoln.DontTakeItem(itemNum);
		
		FindSolns(itemNum + 1,weight);
		//System.out.println("out if item no "+itemNum);
		if(weight <= inst.GetCapacity()){
			
			//System.out.println();
			//System.out.println("takeitemno "+itemNum);
			crntSoln.TakeItem(itemNum);
		
		FindSolns(itemNum + 1,(weight+inst.GetItemWeight(itemNum)));
		}
	
		
	}
}