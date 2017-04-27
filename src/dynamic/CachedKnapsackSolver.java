package dynamic;

import java.util.HashMap;
import java.util.Map;


//TODO stop objects from being created

public class CachedKnapsackSolver extends AbstractKnapsackSolver {

	Map<Integer, Integer>[] subproblems;
	

	@SuppressWarnings("unchecked")
    public CachedKnapsackSolver(KnapsackProblem problem) {
	    super(problem);
	    subproblems = (HashMap<Integer, Integer>[]) new HashMap[noOfItems + 1];
	  
	    for (int i = 0; i < subproblems.length; ++i) {
	    	subproblems[i] = new HashMap<>();
	    }
    }
	
	private int solve(int residualCapacity, int item) {
		if (item <= 0 || residualCapacity <= 0) {
			return 0;							// base case
		}
		
		Integer value = subproblems[item].get(residualCapacity);
		
		if (value == null) {
			subproblems[item].put(
				residualCapacity, residualCapacity < weight(item) ? solve(residualCapacity, item - 1) : 
	    			Math.max(
		    				solve(residualCapacity, item - 1), 								
		    				solve(residualCapacity - weight(item), item - 1) + value(item)
		    			));
		}
		
		return subproblems[item].get(residualCapacity);
	}

	@Override
    public void solve() {
		solve(capacity, noOfItems);
		initializeSolution();
		fillInKnapsack();
    }
	
	@Override
    protected int getValue(int residualCapacity, int item) {
		if (item <= 0 || residualCapacity <= 0) {
			return 0;								// base case as for solve
		}
		
		Integer res = subproblems[item].get(residualCapacity);
	    
	    return (res == null)? -1 : res;				//-1 - value was not even considered, so skip it
    }
	
}
