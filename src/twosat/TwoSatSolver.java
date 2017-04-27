package twosat;

import java.util.Map;
import java.util.TreeMap;

import scc.GreedyGraph;
import scc.KosarajuSCC;


public class TwoSatSolver {
	
	private static final int SIZE_VAL = 0;
	private static final int START = 1;
	
	private int counter = 0;

	private Map<Integer, Integer> toVertices = new TreeMap<Integer, Integer>();
	
	private boolean isSatisfiable = true;
	
	private static int not(int variable) {
		return -variable;
	}

	//input clauses[0] - size of the problem, clauses[...] - pairs of clauses
	public TwoSatSolver(Integer[] clauses)  {		
		
		//! array will contain "holes", but less "if"s will be required
		int size = 4 * clauses[SIZE_VAL];								
		
		GreedyGraph implicationGraph = new GreedyGraph(size);
		
		//construct implication graph and tables
		for (int i = START; i < clauses.length; i += 2) {
			
			toVertices.putIfAbsent(clauses[i], counter++);
			toVertices.putIfAbsent(clauses[i + 1], counter++);
			toVertices.putIfAbsent(not(clauses[i]), counter++);
			toVertices.putIfAbsent(not(clauses[i + 1]), counter++);
			
			implicationGraph.addEdge(toVertices.get(not(clauses[i])), toVertices.get(clauses[i + 1]));
			implicationGraph.addEdge(toVertices.get(not(clauses[i+1])), toVertices.get(clauses[i]));
		}
		
		//run main step of the algorithm
		KosarajuSCC components = new KosarajuSCC(implicationGraph);
		
		components.compute();
		
		int[] scc = components.scc();
		
		for (int variable : toVertices.keySet()) {
			int vertex = toVertices.get(variable);
			int vertexNeg = toVertices.get(not(variable));
			
			if (scc[vertex] == scc[vertexNeg]) {
				isSatisfiable = false;
				
				// contradiction reached
				return;										
			}
		}
	}
		
	public boolean isSatisfiable() {
		return isSatisfiable;
	}

}
