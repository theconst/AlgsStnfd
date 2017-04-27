package dynamic;

abstract public class AbstractKnapsackSolver {
	
	protected boolean[] solution = null;

	protected int capacity;

	protected int[] weights;

	protected int[] values;

	protected int noOfItems;
	
	protected int solutionWeight = -1;
	
	protected int solutionValue = -1;

	public AbstractKnapsackSolver(KnapsackProblem problem) {
		
		//initialize the global data
		this.capacity = problem.getKnapsackCapacity();
		this.weights = problem.getWeights();
		this.values = problem.getValues();
		this.noOfItems = values.length;
	}
	
	abstract public void solve();
	
	protected int value(int j) {
		return values[j - 1];
	}
	
	protected int weight(int j) {
		return weights[j - 1];
	}
	
	
	protected void initializeSolution() {	
	    solution = new boolean[noOfItems]; 					// default value for boolean is false
	}
	
	abstract protected int getValue(int residualCapacity, int item);
	
	
	protected void fillInKnapsack() {
		int currentItem = noOfItems;
	    int currentCapacity = capacity;
	    while (currentItem > 0) {
	    	if (getValue(currentCapacity, currentItem) 
	    			!= getValue(currentCapacity, currentItem - 1)) {
	    		currentCapacity -= weight(currentItem);
	    		
	    		/* add item to the solution, 
	    		 * remembering that output array is labeled 0..n */
	    		solution[currentItem - 1] = true;
	    	}
	    	currentItem = currentItem - 1;
	    }
    }
	
	
	public boolean[] getSolution() {
		if (solution == null) {
			throw new IllegalStateException("Call initializeSolution() first");
		}
		return solution;
	}
	
	public int getSolutionWeight() {
		if (solutionWeight > 0) {
			return solutionWeight;
		}
		
		int weight = 0;
		for (int i = 0; i < solution.length; ++i) {
			weight += solution[i] ? weights[i] : 0;
		}
		return solutionWeight = weight;
	}
	
	public int getSolutionValue() {
		if (solutionValue > 0) {
			return solutionValue;
		}
		
		int value = 0;
		for (int i = 0; i < solution.length; ++i) {
			value += solution[i] ? values[i] : 0;
		}
		return solutionValue = value;
	}
	
}
