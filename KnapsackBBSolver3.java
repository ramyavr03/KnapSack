public class KnapsackBBSolver3
{
protected KnapsackInstance inst;
protected KnapsackSolution soln;
protected KnapsackSolution crntSoln;
protected KnapsackSolution bestSoln;
protected UPPER_BOUND ub3;
	public KnapsackBBSolver3(UPPER_BOUND ub_)
	{
		ub3 = ub_;
	}

	public void dispose()
	{
		if (crntSoln != null)
		{
			
				crntSoln.dispose();
		}
	}

	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		//int currentWeight = 0;
	//	int sumOfValuesTaken = 0;
	//	int sumOfAllCurrentValues = 0;
		int capLeft = 0;
		inst = inst_;
		
    
		InsertionSort(1, inst.GetItemCnt());
    
		bestSoln = soln_;
		capLeft = inst.GetCapacity();
		crntSoln = new KnapsackSolution(inst);
		FindSolns(1,0,0, capLeft);
	}

	public void FindSolns(int itemNum, int currentWeight, int sumOfValuesTaken, int capLeft)
	{
		
		int itemCnt = inst.GetItemCnt();
		int itemWeight = inst.GetItemWeight(itemNum);
		int itemValue = inst.GetItemValue(itemNum);
    
		
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			//printf ("Returning because items finished %d\n", itemNum);
			return;
		}
    
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1, currentWeight, sumOfValuesTaken, capLeft);
    
		if (currentWeight + itemWeight <= inst.GetCapacity())
		{
			sumOfValuesTaken = sumOfValuesTaken + itemValue;
			capLeft = capLeft - itemWeight;
			if (bestSoln.GetValue() < (sumOfValuesTaken + (FindUpperBoundUsingFractionalKnapSack(itemNum + 1, capLeft))))
			{
				crntSoln.TakeItem(itemNum);
				currentWeight = currentWeight + itemWeight;
				FindSolns(itemNum + 1, currentWeight, sumOfValuesTaken, capLeft);
			}
			else
			{
				//printf ("Returning because branch and bound condition %d %d\n", bestSoln->GetValue(), (sumOfValuesTaken + (sumOfAllValues - sumOfAllCurrentValues)));
				CheckCrntSoln();
				return;
			}
		}
		else
		{
			//printf ("Returning because Weight Exceeding %d %d\n", currentWeight + itemWeight, inst->GetCapacity());
			CheckCrntSoln();
		}
	}

public int FindUpperBoundUsingFractionalKnapSack(int itemNum, int capLeft)
	{
		int upperBoundValue = 0;
		for (int i = itemNum; i <= inst.GetItemCnt(); i++)
		{
			if (capLeft >= inst.GetItemWeight(i))
			{
				capLeft = capLeft - inst.GetItemWeight(i);
				upperBoundValue = upperBoundValue + inst.GetItemValue(i);
			}
			else
			{
				upperBoundValue = (int) (upperBoundValue + (capLeft * inst.GetItemValuePerWeight(i)));
				break;
			}
		}
		return upperBoundValue;
	}

	public void CheckCrntSoln()
	{
		int crntVal = crntSoln.ComputeValue();
    
	
		System.out.print("\nChecking solution ");
		crntSoln.Print(" ");
	
    
		if (crntVal == DefineConstants.INVALID_VALUE)
		{
			return;
		}
    
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

//C++ TO JAVA CONVERTER WARNING: The original C++ declaration of the following method implementation was not found:
	public void InsertionSort(int lo, int hi)
	{
		int j = lo;
			for (int i = lo + 1; i <= hi; ++i)
			{
				int key1 = inst.GetItemWeight(i);
			int key2 = inst.GetItemValue(i);
			float key3 = inst.GetItemValuePerWeight(i);
				j = i - 1;
    
				while (j >= lo && (inst.GetItemValuePerWeight(j) < key3))
				{
				inst.SetItemWeight(j + 1,inst.GetItemWeight(j));
				inst.SetItemValue(j + 1,inst.GetItemValue(j));
				inst.SetItemValuePerWeight(j + 1,inst.GetItemValuePerWeight(j));
						j = j - 1;
				}
			inst.SetItemWeight(j + 1,key1);
			inst.SetItemValue(j + 1,key2);
			inst.SetItemValuePerWeight(j + 1,key3);
			}
	}


}