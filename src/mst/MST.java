package mst;

import graph.UndirectedGraph;

import java.util.HashSet;
import java.util.Set;


/**
 * Wrapper class for the graph
 * 
 * @author Koshak
 */
abstract public class MST {
	protected UndirectedGraph mst = new UndirectedGraph();
	
	protected Set<Integer> marked;
	
	protected double cost = 0;
	
	/**
	 * Implementation based on the priority queue that contains edges
	 * 
	 * @param target target graph
	 */
	public MST(UndirectedGraph target) {
		if (target.isEmpty()) {
			return;					
		}
		marked = new HashSet<>(target.cardE());
	}
	
	public UndirectedGraph getMST() {
		return mst;
	}
	
	public double cost() {
		return cost;
	}
	
	public String toString() {
		return mst.toString();
	}
}
