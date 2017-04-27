package shortest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import graph.DirectedGraph;
import graph.WeightedEdge;


//as usual, algorithm has fragile dependence on vertex enumeration
abstract  public class AbstractSP {

	private static final String ILLEGAL_STATE_MESSAGE = "call findShortesPathFrom first!";
	protected int[] edgeTo;							//contains the id's of vertexes
	protected double[] distTo;
	protected DirectedGraph target;
	protected int source = -1;
	
	
	public AbstractSP(DirectedGraph target) {
		this.target = target;
		edgeTo = new int[target.cardV()];			//filled with zeros
		distTo = new double[target.cardV()];
		
		Arrays.fill(edgeTo, -1);
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
	}
	
	public void findShortestPathFrom(int source) {
		if (!target.vertexes().contains(source)) {
			throw new IllegalArgumentException("the vertex doesn't exists");
		}
		
		this.source = source;
		edgeTo[source] = source;
		distTo[source] = 0;
		
		handleSP();									//call to the template method
	}
	
	abstract protected void handleSP();


	public int getEdgeTo(int sink) {
		return edgeTo[sink];
	}
	
	public List<WeightedEdge> getShortestPathTo(int vertex) {
		checkState();
		
		if (edgeTo[vertex] < 0) {
			assert(distTo[vertex] < 0);
			return null;				//vertex is not reachable
		}
		
		
		LinkedList<WeightedEdge> path = new LinkedList<>();

		for (int cursor = vertex; cursor != source; cursor = edgeTo[cursor]) {
			int from = edgeTo[cursor];
			int to = cursor;
			double weight = distTo[to] - distTo[from];
			
			path.addFirst(new WeightedEdge(from, to, weight));
		}
		
		return path;	
	}

	
	public double getDistTo(int vertex) {
		checkState();
		
		return distTo[vertex];
	}

	private void checkState() {
	    if (source < 0) {
			throw new IllegalStateException(ILLEGAL_STATE_MESSAGE);
		}
    }
	
	protected void relax(WeightedEdge e) {
		int from = e.from();
		int to = e.to();
		
		//distance using this edge
		double possibleDistance = distTo[from] + e.weight();
		if (distTo[to] > possibleDistance) {
			distTo[to] = possibleDistance;
			edgeTo[to] = from;
		}
	}
	
	protected void relax(int v) {
		for (WeightedEdge we : target.adj(v)) {
			relax(we);
		}
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		checkState();
		
		sb.append("Shortest path {").append(System.lineSeparator());
		for (int i = 0; i < target.cardV(); ++i) {
			sb.append(getShortestPathTo(i)).
			append("{").append(distTo[i]).append("}").
			append(System.lineSeparator());
		}
		
		return sb.append("}").toString();
	}

}
