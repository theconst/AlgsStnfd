package graph;

import java.util.Set;


public class DirectedGraph extends AbstractGraph {
	
	public DirectedGraph() {
		this(1, 1);
	}
	
	public DirectedGraph(int cardV, int cardE) {
		super(cardV, cardE);
	}

	public DirectedGraph reverse() {    
		DirectedGraph reverse = new DirectedGraph(cardV(), cardE());
		
		Set<Integer> vert = vertexes();
		for (int v: vert) {
			for (WeightedEdge we : this.adj(v)) {
				reverse.addEdge(new WeightedEdge(we.to(), we.from(), we.weight()));
			}
		}		
		return reverse;	
    }

}
