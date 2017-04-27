package dynamic;


//kill yourself with indexes
//TODO REDO this
public class StraightKnapsackSolver extends AbstractKnapsackSolver {
	
	private int[][] subproblems;

	public StraightKnapsackSolver(KnapsackProblem problem) {
	    super(problem);
	   
    }
	
	@Override
	protected int getValue(int residualCapacity, int item) {
		return (item < 0 || residualCapacity < 0) ? 0 : subproblems[residualCapacity][item];
	}
	
	@Override
    public void solve() {
	    subproblems = new int[capacity + 1][noOfItems + 1];							//arrays are zero by default
	   
	    for (int res = 0; res <= capacity; ++res) {									//res - residual capacity
	    	for (int j = 1; j <= noOfItems; ++j) {									//j - item index
	    		subproblems[res][j] = res < weight(j) ?  getValue(res, j - 1) : 
	    			Math.max(
	    				getValue(res, j - 1), 								
	    				getValue(res - weight(j), j - 1) + value(j)
	    			);
	    	}
	    }

	    initializeSolution();
	    fillInKnapsack();
	}
	

}
