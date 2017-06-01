
// Brute-force solver
public class KnapsackBFSolver
{
	protected KnapsackInstance inst;
	protected KnapsackSolution soln;
	protected KnapsackSolution crntSoln;
	protected KnapsackSolution bestSoln;

	/********************************************************************/

	protected void FindSolns(int itemNum)
	{
		//System.out.println("in FS itmno "+itemNum);
		int itemCnt = inst.GetItemCnt();

		if (itemNum == itemCnt + 1)
		{
			//System.out.println("before checkcrntsol ");
			CheckCrntSoln();
			//System.out.println("after checkcrntsol");
			return;
		}

		crntSoln.DontTakeItem(itemNum);

		//System.out.println();
		//System.out.println("don takeitemno "+itemNum);
		FindSolns(itemNum + 1);

		crntSoln.TakeItem(itemNum);
		//System.out.println();
		//System.out.println("takeitemno "+itemNum);

		FindSolns(itemNum + 1);
	
	}
	/********************************************************************/

	protected void CheckCrntSoln()
	{
		int crntVal = crntSoln.ComputeValue();
		//System.out.println("curnt val "+crntVal);
//C++ TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
///#if KNAPSACK_DEBUG
		//System.out.print("\nChecking solution ");
		//crntSoln.Print(" ");
///#endif

		if (crntVal == DefineConstants.INVALID_VALUE)
			return;

		if (bestSoln.GetValue() == DefineConstants.INVALID_VALUE) //The first solution is initially the best solution
		{
			bestSoln.Copy(crntSoln);
		}
		else
		{
			if (crntVal > bestSoln.GetValue())
			{
				bestSoln.Copy(crntSoln);
			}
		}
	}

	/*****************************************************************************/

	public KnapsackBFSolver()
	{
		crntSoln = null;
	}
	/********************************************************************/

	public void dispose()
	{
		if (crntSoln != null)
		{
			
				crntSoln.dispose();
		}
	}
	/********************************************************************/

	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		bestSoln = soln_;
		crntSoln = new KnapsackSolution(inst);
		FindSolns(1);
	}
}