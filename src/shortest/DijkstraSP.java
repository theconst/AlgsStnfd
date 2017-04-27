package shortest;

import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;

import graph.DirectedGraph;
import graph.WeightedEdge;

//TODO Since this implementation is no better than quadratic in worst case, do my own priority queue

public class DijkstraSP extends AbstractSP {

	public DijkstraSP(DirectedGraph target) {
	    super(target);
    }

	@Override
    protected void handleSP() {
		int cardV = target.cardV();
	    Comparator<Integer> byDistance = (first, second) -> Double.compare(distTo[first], distTo[second]);
		PriorityQueue<Integer> vertices = new PriorityQueue<>(cardV, byDistance);
		
		
		//with bit set we have minimum overhead and don't need to process the vertexes
		BitSet marked = new BitSet(cardV);
	
		vertices.add(source);
		
		int shortestReachable;
		do {
			
			shortestReachable = vertices.poll();
			
			/* Order of relax and adding all vertexes matters*/
			
			/*
			 * if edge was relaxed, it can no more be eligible, 
			 * because it means either 1. cycle 2. the incorrectness of previous step
			 */
			relax(shortestReachable);
			marked.set(shortestReachable, true);
			
			/* add all not explored adjacent vertexes to priority queue */
			for (WeightedEdge we : target.adj(shortestReachable)) {
				int to = we.to();
				vertices.remove(to);				//! bottleneck here
				if (!marked.get(to)) {
					vertices.add(to);
				}
			}
			
			if (vertices.isEmpty()) {
				break;									//case if graph is connected
			}
		} while ((distTo[shortestReachable] < Double.POSITIVE_INFINITY));
    }
	
	
	@Override
	protected void relax(WeightedEdge e) {
		if (e.weight() < 0) {
			throw new IllegalArgumentException("Edge weights should be positive");
		}
		super.relax(e);
		//can add to PQ here
	}

}
