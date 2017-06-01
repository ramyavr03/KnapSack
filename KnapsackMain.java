import java.util.Calendar;


public class KnapsackMain
{

	public static int gRefTime = 0;
/********************************************************************/
/********************************************************************/

	public static void SetTime()
	{
		
		gRefTime = GetCurrentTime();
		System.out.println("greftim "+gRefTime);
	}
/********************************************************************/

	public static int GetTime()
	{
		int crntTime = GetCurrentTime();
		System.out.print("crnttime "+crntTime+"gRefTime "+gRefTime+"diff "+(crntTime-gRefTime));
		return (crntTime - gRefTime);
	}

	public static void main(String[] args)
	{
		
		int time;
		Long BFTime;
        float speedup;
		int itemCnt;
		KnapsackInstance inst; //a Knapsack instance object
		KnapsackBFSolver DPSolver = new KnapsackBFSolver(); //brute-force solver
		KnapsackBFSolver BFSolver = new KnapsackBFSolver(); //brute-force solver
		KnapsackBTSolver BTSolver = new KnapsackBTSolver(); //backtracking solver
		KnapsackBBSolver1 BBSolver1 = new KnapsackBBSolver1(UPPER_BOUND.UB1); //branch-and-bound solver
		KnapsackBBSolver2 BBSolver2 = new KnapsackBBSolver2(UPPER_BOUND.UB2);
	    KnapsackBBSolver3 BBSolver3 =new KnapsackBBSolver3(UPPER_BOUND.UB3);		
		KnapsackSolution DPSoln;
		KnapsackSolution BFSoln;
		KnapsackSolution BTSoln;
		KnapsackSolution BBSoln1;
		KnapsackSolution BBSoln2;
		KnapsackSolution BBSoln3;

		if (args.length != 1)
		{
			System.out.print("Invalid Number of command-line arguments\n");
			System.exit(1);
		}
		itemCnt = Integer.parseInt(args[0]);
		if (itemCnt < 1)
		{
			System.out.print("Invalid number of items\n");
			System.exit(1);
		}

		inst = new KnapsackInstance(itemCnt);
		DPSoln = new KnapsackSolution(inst);
		BFSoln = new KnapsackSolution(inst);
		BTSoln = new KnapsackSolution(inst);
		BBSoln1 = new KnapsackSolution(inst);
		BBSoln2 = new KnapsackSolution(inst);
		BBSoln3 = new KnapsackSolution(inst);
		inst.Generate();
		inst.Print();

		//SetTime();
		Long  startTime = System.nanoTime();
       DPSolver.Solve(inst, DPSoln);
		Long elapsed = System.nanoTime()-startTime;
		time =  (int)(elapsed/1000000);
		System.out.println();
		System.out.printf("\n\nSolved using dynamic programming (DP) in "+time+" ms Optimal value = "+DPSoln.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
		DPSoln.Print("Dynamic Programming Solution");
		}
		/***********************************************************************************/
	
		
		//SetTime();
		startTime = System.nanoTime();
     	BFSolver.Solve(inst, BFSoln);
		elapsed = System.nanoTime()-startTime;
		//BFTime = time = GetTime();
		BFTime = (elapsed/1000000);
		System.out.printf("\n\nSolved using brute-force enumeration (BF) in "+BFTime+" ms Optimal value = "+ BFSoln.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
			BFSoln.Print("Brute-Force Solution");
		}
		if (DPSoln.equalsTo(BFSoln))
		{
			System.out.print("\nSUCCESS: DP and BF solutions match");
		}
		else
		{
			System.out.print("\nERROR: DP and BF solutions mismatch");
		}
		/***********************************************************************************/
		
		//SetTime();
		startTime = System.nanoTime();
   BTSolver.Solve(inst, BTSoln);
		elapsed = System.nanoTime()-startTime;
		//time = GetTime();
		time =(int) (elapsed/1000000);
		System.out.printf("\n\nSolved using backtracking (BT) in "+(elapsed/1000000)+" Optimal value = "+ BTSoln.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
			BTSoln.Print("Backtracking Solution");
		}
		if (BFSoln.equalsTo(BTSoln))
		{
			System.out.print("\nSUCCESS: BF and BT solutions match");
		}
		else
		{
			System.out.print("\nERROR: BF and BT solutions mismatch");
		}
		speedup = (float) (time == 0? 0 : 100.0 * (BFTime - time) / (float)BFTime);
	    System.out.printf("\nSpeedup of BT relative to BF is %.2f%c",speedup,'%');
	    /***********************************************************************************/
		
		//SetTime();
	    startTime = System.nanoTime();
      BBSolver1.Solve(inst, BBSoln1);
	    elapsed = System.nanoTime()-startTime;
		//time = GetTime();
	    time =(int) (elapsed/1000000);
		System.out.printf("\n\nSolved using branch-and-bound (BB) with UB1 "+(elapsed/1000000)+" Optimal value = "+ BBSoln1.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
			BBSoln1.Print("BB-UB1 Solution");
		}
		if (BFSoln.equalsTo(BBSoln1))
		{
			System.out.print("\nSUCCESS: BF and BB-UB1 solutions match");
		}
		else
		{
			System.out.print("\nERROR: BF and BB-UB1 solutions mismatch");
		}
		speedup = (float) (time == 0? 0 : 100.0 * (BFTime - time) / (float)BFTime);
		System.out.printf("\nSpeedup of BB-UB1 relative to BF is %.2f%c",speedup,'%');
		/***********************************************************************************/
		    startTime = System.nanoTime();
	BBSolver2.Solve(inst, BBSoln2);
		    elapsed = System.nanoTime()-startTime;
			//time = GetTime();
		    time =(int) (elapsed/1000000);
			System.out.printf("\n\nSolved using branch-and-bound (BB) with UB2 "+(elapsed/1000000)+" Optimal value = "+ BBSoln2.GetValue());
			if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
			{
				BBSoln2.Print("BB-UB2 Solution");
			}
			if (BFSoln.equalsTo(BBSoln2))
			{
				System.out.print("\nSUCCESS: BF and BB-UB2 solutions match");
			}
			else
			{
				System.out.print("\nERROR: BF and BB-UB2 solutions mismatch");
			}
			speedup = (float) (time == 0? 0 : 100.0 * (BFTime - time) / (float)BFTime);
			System.out.printf("\nSpeedup of BB-UB2 relative to BF is %.2f%c",speedup,'%');
		/***********************************************************************************/
			 startTime = System.nanoTime();
		BBSolver3.Solve(inst, BBSoln3);
			    elapsed = System.nanoTime()-startTime;
				//time = GetTime();
			    time =(int) (elapsed/1000000);
				System.out.printf("\n\nSolved using branch-and-bound (BB) with UB3 "+(elapsed/1000000)+" Optimal value = "+ BBSoln3.GetValue());
				if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
				{
					BBSoln3.Print("BB-UB3 Solution");
				}
				if (BFSoln.equalsTo(BBSoln3))
				{
					System.out.print("\nSUCCESS: BF and BB-UB3 solutions match");
				}
				else
				{
					System.out.print("\nERROR: BF and BB-UB3 solutions mismatch");
				}
				speedup = (float) (time == 0? 0 : 100.0 * (BFTime - time) / (float)BFTime);
				System.out.printf("\nSpeedup of BB-UB3 relative to BF is %.2f%c",speedup,'%');
			
			
			/***********************************************************************************/
			
		if (inst != null)
		inst.dispose();
		if (DPSoln != null)
		DPSoln.dispose();
		if (BFSoln != null)
		BFSoln.dispose();
		if (BTSoln != null)
		BTSoln.dispose();
		if  (BBSoln1 != null)
		BBSoln1.dispose();
		if  (BBSoln2 != null)
		BBSoln2.dispose();
		if(BBSoln3 != null)
		BBSoln3.dispose();

		System.out.print("\n\nProgram Completed Successfully\n");

	}
	/*****************************************************************************/

	public static int GetCurrentTime()
	{
		int crntTime = 0;
		
		crntTime = Calendar.getInstance().get(Calendar.MILLISECOND);//GetMilliSecondTime(timeBuf);
        //System.out.println("crnt time "+crntTime);
		return crntTime;
	}
}