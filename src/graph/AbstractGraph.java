package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class AbstractGraph {
	
	
	/* Default implementations for directed graphs */
	
	protected Map<Integer, Set<WeightedEdge>> edges;
	
	public AbstractGraph() {
		this(1, 1);
	}
	
	public AbstractGraph(int cardV, int cardE) {
		edges = new HashMap<>(cardE);
	}
	
	public void addVertex(int vertex) {
		if (edges.get(vertex) == null) {
			edges.put(vertex, new HashSet<>());
		}
	}
	
	public void addEdge(int from, int to, double weight) {
		this.addEdge(new WeightedEdge(from, to, weight));
	}
	
	
	//method to override
	public void addEdge(WeightedEdge edge) {
		addVertex(edge.from());
		addVertex(edge.to());
		edges.get(edge.from()).add(edge);
	}
	
	public Set<WeightedEdge> adj(int v) {
		return edges.get(v);
	}
	
	
	public Set<Integer> vertexes() {
		return edges.keySet();
	}
	
	public Set<WeightedEdge> edges() {
		Set<WeightedEdge> edges = new HashSet<>();
		for (Integer v: vertexes()) {
			for (WeightedEdge e: adj(v)) {
				edges.add(e);
			}
		}
		return edges;
	}
	
	public int cardE() {
		int sum = 0;
		for (Integer v: edges.keySet()) {
			sum += edges.get(v).size();
		}
		return sum;
	}
	
	public int cardV() {
		return edges.keySet().size();
	}
	
	public int source() {
		if (isEmpty()) {
			return -1;
		}
		
		return vertexes().iterator().next();
	}
	
	public boolean isEmpty() {
		return edges.isEmpty();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Graph").append("(").append("V=").append(cardV())
			.append(", E=").append(cardE()).append(") ").append("{")
			.append(System.lineSeparator());
		
		for (Integer v: vertexes()) {
		//	sb.append(v.toString()).append(": ");
			for (WeightedEdge we: edges.get(v)) {
				sb.append(we.toString()).append(System.lineSeparator());
			}
			sb.append(System.lineSeparator());
		}
		sb.append("}");
		
		return sb.toString();
	}

}
