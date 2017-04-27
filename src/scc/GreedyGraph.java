package scc;

import java.util.ArrayList;
import java.util.List;

public class GreedyGraph {
	
	//? add specialized implementation
	List<Integer>[]	adjacencyList;


	
	// well, should have done with array list with preset initial capacity
	@SuppressWarnings("unchecked")
    public GreedyGraph(int cardV) {
		if (cardV < 0) {
			throw new IllegalArgumentException("Vertex count should be positive");
		}

		adjacencyList = (List<Integer> []) new ArrayList[cardV];
			
		for (int i = 0; i < cardV; ++i) {
			adjacencyList[i] = new ArrayList<>();
		}	
	}

	//assume no dupblicate edges, but these are allowed
	public void addEdge(int from, int to) {
		checkVertex(from);
		checkVertex(to);
		adjacencyList[from].add(to);
	}
	

	//iterable
	public Iterable<Integer> adj(int v) {
		checkVertex(v);


		return adjacencyList[v];
	}

  	public int cardV() {
		return adjacencyList.length;
	}
	

	//linear in size of intput
	public int cardE() {
		int result = 0;
 		
		for (int i = 0; i < adjacencyList.length; i++) {
			result += adjacencyList[i].size();
		}

		return result;
	}
	
	
	public GreedyGraph reverse() {
		GreedyGraph result = new GreedyGraph(adjacencyList.length);
		
		for (int from = 0; from < adjacencyList.length; from++) {
			for (Integer to : adj(from)) {
				result.addEdge(to, from);
			}
		}
		return result;
	}	
	
	private void checkVertex(int vertex) {
		if (vertex < 0 || vertex >= adjacencyList.length) {
			throw new IllegalArgumentException("The vertex is illegal: " + vertex);	
		}
	}
				
}