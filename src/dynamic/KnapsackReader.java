package dynamic;

import java.util.Iterator;

import common.IntParser;

public class KnapsackReader extends IntParser {
	

	public KnapsackReader(String pathToFile) throws Exception {
	    super(pathToFile);
    }
	
	
	public KnapsackProblem getKnapsackSubproblem() {
		
		Iterator<Integer> it = ints.iterator();
		
		int knapsackSize = it.next();
		int noOfItems = it.next();
		
		int[] values = new int[noOfItems];
		int[] weights = new int[noOfItems];
		
		for(int k = 0; it.hasNext(); k++) {
			values[k] = it.next();
			weights[k] = it.next();
		}
		
		return new KnapsackProblem(knapsackSize, values, weights);
	}

}
