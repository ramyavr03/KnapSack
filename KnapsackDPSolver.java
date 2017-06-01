// Dynamic programming solver
public class KnapsackDPSolver extends KnapsackBFSolver
{
	//protected KnapsackInstance inst;
	//protected KnapsackSolution soln; 
	/********************************************************************/

	

	public KnapsackDPSolver()
	{
		
	}
	public void dispose()
	{

	}
	public final void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		soln = soln_;
    
		int itemCnt = inst.GetItemCnt();
		int capacity = inst.GetCapacity();
    
		int[][] dpTable = new int[itemCnt + 1][capacity + 1];
    
		for (int j = 0; j <= capacity; j++)
		{
			dpTable[0][j] = 0;
		}
    
		for (int i = 1; i <= itemCnt ; i++)
		{
			dpTable[i][0] = 0;
			int itemWeight = inst.GetItemWeight(i);
			int itemValue = inst.GetItemValue(i);
    
			for (int j = 1; j <= capacity ; j++)
			{
				if (j < itemWeight)
				{
					dpTable[i][j] = dpTable[i - 1][j];
				}
				else
				{
					if (dpTable[i - 1][j] > (itemValue + dpTable[i - 1][j - itemWeight]))
					{
						dpTable[i][j] = dpTable[i - 1][j];
					}
					else
					{
						dpTable[i][j] = itemValue + dpTable[i - 1][j - itemWeight];
					}
				}
			}
		}
    
		/*//Print Table
		for (int i =0; i <= itemCnt; i++)
		{
			for (int j =0; j <= capacity; j++)
			{
				printf("%d ",dpTable[i][j]);
			}
			printf("\n");
		}*/
    
		//Scan table to get the taken items
		int j = capacity;
		for (int i = itemCnt ; i >= 1 ; i--)
		{
			if (dpTable[i][j] != dpTable[i - 1][j])
			{
				soln_.TakeItem(i);
				j = j - inst.GetItemWeight(i);
			}
		}
    
		//Compute the value for taken items
		soln_.ComputeValue();
	}
}