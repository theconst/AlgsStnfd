package mst;

import graph.UndirectedGraph;
import graph.WeightedEdge;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class PrimMST extends MST {

	/**
	 * Implementation based on the priority queue that contains edges
	 * 
	 * @param target target graph
	 */
	public PrimMST(UndirectedGraph target) {
		super(target);
		
		if (target.isEmpty()) {
			return;					
		}
		
		marked = new HashSet<>(target.cardE());
		
		PriorityQueue<WeightedEdge> unspanned = new PriorityQueue<>(target.cardV(),
			new Comparator<WeightedEdge>() {

				@Override
				public int compare(WeightedEdge first, WeightedEdge second) {
					return Double.compare(first.weight(), second.weight());
				}
			
		});
		
		int source = target.source();
		unspanned.addAll(target.adj(source));
		marked.add(source);
		
		while (!unspanned.isEmpty()) {
			WeightedEdge next = unspanned.remove();
			
			/*
				if (marked.size() == target.cardV()) {
					break;				
				} 
			*/
			if (!isMarked(next.to())) {
				mst.addEdge(next);
				cost += next.weight();
				unspanned.addAll(target.adj(next.to()));
				marked.add(next.to());
			}
		}
		
	}
	
	private boolean isMarked(int v) {
		return marked.contains(v);
	}
	
}
