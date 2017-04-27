package shortest;

import graph.DirectedGraph;
import graph.WeightedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

abstract public class AbstractAPSP {

	protected int[][] edgeTo;				//edgeTo[i][j] = {#of edge} going to (j) if source is (i)
	
	protected double[][] distTo;			//distTo[i][j] = {distance to (j)} if source is (i)

	protected DirectedGraph target;
	
	boolean called = false;
	
	private void checkState() {
	    if (!called) {
			throw new IllegalStateException("Call compute first!");
		}
    } 
	
	public AbstractAPSP(DirectedGraph target) {
		this.target = target;
		edgeTo = new int[target.cardV()][target.cardV()];			//filled with zeros
		distTo = new double[target.cardV()][target.cardV()];
		
		//initialize arrays
		for (int i = 0; i < target.cardV(); ++i) {
			
			/* fill initial edges */
			Arrays.fill(edgeTo[i], -1);
			edgeTo[i][i] = i;
			
			Arrays.fill(distTo[i], Double.POSITIVE_INFINITY);
			distTo[i][i] = 0;
		}
	}
	
	public void compute() {
		called = true;
		findShortestPaths();
	}
	
	abstract protected void findShortestPaths();
	
	
	public double getDistanceTo(int source, int sink) {
		checkState();
		return distTo[source][sink];
	}
	
	public List<WeightedEdge> getShortestPath(int source, int sink) {
		checkState();
		
		if (edgeTo[source][sink] < 0) {
			return null;				//vertex is not reachable
		}
		
		LinkedList<WeightedEdge> path = new LinkedList<>();

		for (int cursor = sink; cursor != source; cursor = edgeTo[source][cursor]) {
			
			int from = edgeTo[source][cursor];
			int to = cursor;
			double weight = distTo[source][to] - distTo[source][from];
			
			path.addFirst(new WeightedEdge(from, to, weight));
		}
		
		return path;
	}
	
	
	public String toString() {
		checkState();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Distances{\n").append(Arrays.deepToString(distTo));
		sb.append("}\nPaths{\n");
		for (int source = 0; source < edgeTo.length; source++) {
			for (int sink = 0; sink != source; sink = edgeTo[source][sink]) { 
				sb.append(getShortestPath(source, sink));
			}
		}
		return sb.append("\n}").toString();
	}
	
}
