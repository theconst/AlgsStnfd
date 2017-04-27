package mst;

import graph.UndirectedGraph;
import graph.WeightedEdge;

import java.util.Comparator;
import java.util.PriorityQueue;

import unionfind.UnionFind;

/**
 * 
 * Kruskal's algorithm for MST
 * 
 * @author Koshak
 */
public class KruskalMST extends MST {
	
	protected UnionFind spannedVertexes;
	
	protected PriorityQueue<WeightedEdge> unspannedEdges;

	public KruskalMST(UndirectedGraph target) {
		super(target);
		
		spannedVertexes = new UnionFind(target.cardV());
		
		unspannedEdges = new PriorityQueue<>(target.cardE(),
				new Comparator<WeightedEdge>() {

					@Override
					public int compare(WeightedEdge first, WeightedEdge second) {
						return Double.compare(first.weight(), second.weight());
					}		
			});
		
		for (Integer v: target.vertexes()) {
			unspannedEdges.addAll(target.adj(v));
		}
		
		while (!isFinished(target)) {
			WeightedEdge min = unspannedEdges.poll();
			
			if (!closesCycle(min)) {
				mst.addEdge(min);
				cost += min.weight();
				union(min.from(), min.to());
			}
		}
	}
	
	
	/* The stopping criterion for the clustering */
	protected boolean isFinished(UndirectedGraph target) {
		return (mst.cardE() == target.cardV() - 1);
	}
	
	protected boolean closesCycle(WeightedEdge e) {
		return spannedVertexes.areEqueal(id(e.from()), id(e.to()));
	}
	
	protected void union(int w, int v) {
		spannedVertexes.union(id(w), id(v));
	}
	
	
	//renumerate vertexes
	protected int id(int v) {
		return v - 1;
	}
}
