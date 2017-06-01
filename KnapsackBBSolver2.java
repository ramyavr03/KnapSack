
public class KnapsackBBSolver2 extends KnapsackBFSolver {
	 protected UPPER_BOUND ub2;
     public int total_value=0;
    
    public KnapsackBBSolver2(UPPER_BOUND ub_)
	{
    	ub2 = ub_;
	}
	public void dispose()
	{
    super.dispose();
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		bestSoln = soln_;
    	crntSoln = new KnapsackSolution(inst);
		for(int i =0; i <=inst.GetItemCnt();i++)
		{
			total_value = total_value+inst.GetItemValue(i);
		}
		
    	FindSolns(1,0,0);
		
	}
	public void FindSolns(int itemNum,int weight,int value){
        int capLeft=0;
        int upperbound =0;
		int itemCnt = inst.GetItemCnt();
		if(weight > inst.GetCapacity()){
			return;
		}
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;		
		}
		
		
		crntSoln.DontTakeItem(itemNum);
		capLeft = inst.GetCapacity()- weight;
		for(int i = itemNum;i <=inst.GetItemCnt();i++)
		{
			if( inst.GetItemWeight(i) <= capLeft)
			{
				upperbound= upperbound+inst.GetItemValue(i);
			}
		}
		FindSolns(itemNum + 1,weight,value);
		if(weight <= inst.GetCapacity() && (value+upperbound) > bestSoln.GetValue()){
			
			crntSoln.TakeItem(itemNum);		
		FindSolns(itemNum + 1,weight+inst.GetItemWeight(itemNum),value+inst.GetItemValue(itemNum));
		}
	
	}
}