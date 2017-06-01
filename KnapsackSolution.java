
public class KnapsackSolution
{
	private boolean[] isTaken;
	private int value;
	
	private KnapsackInstance inst;

	/*****************************************************************************/

	public KnapsackSolution(KnapsackInstance inst_)
	{
		int i;
		int itemCnt = inst_.GetItemCnt();

		inst = inst_;
		isTaken = new boolean[itemCnt + 1];
		value = DefineConstants.INVALID_VALUE;

		for (i = 1; i <= itemCnt; i++)
		{
			isTaken[i] = false;
		}
	}
	/********************************************************************/

	public void dispose()
	{
		isTaken = null;
	}

	/********************************************************************/

	public boolean equalsTo (KnapsackSolution otherSoln)
	{
		return value == otherSoln.value;
	}
	/********************************************************************/

	public final void TakeItem(int itemNum)
	{
		//System.out.println("in take item");
		isTaken[itemNum] = true;
	
	}
	/********************************************************************/

	public final void DontTakeItem(int itemNum)
	{
		//System.out.println("in don take item");
		isTaken[itemNum] = false;
	
	}
	/********************************************************************/

	public final int ComputeValue()
	{
		int i;
		int itemCnt = inst.GetItemCnt();
		int weight = 0;
		//System.out.println("itm cnt "+itemCnt);
		value = 0;
		for (i = 1; i <= itemCnt; i++)
		{
			if (isTaken[i] == true)
			{
				weight += inst.GetItemWeight(i);
				if (weight > inst.GetCapacity())
				{
					value = DefineConstants.INVALID_VALUE;
					break;
				}
				value += inst.GetItemValue(i);
			}
		}
		return value;
	}
	/********************************************************************/

	public final int GetValue()
	{
		return value;
	}
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//	int GetWeight();
	/********************************************************************/

	public final void Print(String title)
	{
		int i;
		int itemCnt = inst.GetItemCnt();

		System.out.printf("\n%s: ",title);
		for (i = 1; i <= itemCnt; i++)
		{
			if (isTaken[i] == true)
			{
				System.out.printf("%d ",i);
			}
		}
		System.out.printf("\nValue = %d\n",value);

	}
	/********************************************************************/

	public final void Copy(KnapsackSolution otherSoln)
	{
		int i;
		int itemCnt = inst.GetItemCnt();

		for (i = 1; i <= itemCnt; i++)
		{
			isTaken[i] = otherSoln.isTaken[i];
		}
		value = otherSoln.value;
	}
}