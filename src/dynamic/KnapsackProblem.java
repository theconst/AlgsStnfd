package dynamic;


/**
 * Immutable class for problem solving
 */
public class KnapsackProblem {
	
	private int knapsackCapacity;
	private int[] values;
	private int[] weights;
	
	
	public KnapsackProblem(int knapsackSize, int[] values, int[] weights) {
	    super();
	    this.knapsackCapacity = knapsackSize;
	    this.values = values;
	    this.weights = weights;
	}

	public int getKnapsackCapacity() {
		return this.knapsackCapacity;
	}


	public int[] getValues() {
		return this.values;
	}

	public int[] getWeights() {
		return this.weights;
	}
	
	
}
