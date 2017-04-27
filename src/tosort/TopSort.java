package tosort;

import java.util.BitSet;
import java.util.LinkedList;

import graph.DirectedGraph;
import graph.WeightedEdge;


//TODO: test the class


public class TopSort {

	private DirectedGraph target;
	private int[] vertices;
	private boolean hasCycle;
	private BitSet marked;
	
	private boolean valid = false;

	public TopSort(DirectedGraph target) {
		this.target = target;
		this.vertices = new int[target.cardV()];
		this.hasCycle = false;
		this.marked = new BitSet(target.cardV());
	}
	
	public int[] order() {
		checkState();
		if (hasCycle) {
			return new int[0];							//return dummy
		}
		return this.vertices;
	}
	
	
	private void checkState() {
	    if (!valid) {
	    	throw new IllegalStateException("call compute first!");
	    }	    
    }
	
	private void mark (int v) {
		checkBounds(v);
		marked.set(v);
	}
	
	private boolean isMarked(int v) {
		checkBounds(v);
		return marked.get(v);
	}

	private void checkBounds(int v) {
	    if (v < 0 || v > target.cardV()) {
			throw new IllegalArgumentException("check marked");
		}
    }

	public void compute() {
		valid = true;
		
		LinkedList<Integer> toVisit = new LinkedList<>();
		
		toVisit.addFirst(target.source());
		
		int pointer = 0;									//points to place in vertices array
		
		while (!toVisit.isEmpty()) {
			int next = toVisit.poll();
			vertices[pointer++] = next;
			mark(next);										//mark as visited
			
			for (WeightedEdge we : target.adj(next)) {
				int other = we.from();
				
				if (!isMarked(other)) {
					hasCycle = true;
					return;
				}
				toVisit.addFirst(other);
			}
		}
		
	}
}
