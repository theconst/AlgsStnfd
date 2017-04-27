package scc;


import java.util.BitSet;
import java.util.Iterator;
import java.util.Stack;


/**
 * Depth first search that delegates its methods
 * 
 * @author 
 *
 */
public class DepthFirstSearch {
	protected GreedyGraph target;
	protected BitSet marked;

	
	public DepthFirstSearch(GreedyGraph target) {
		this.target = target;
		this.marked = new BitSet(target.cardV());
	}


	protected void mark(int v) {
		checkBounds(v);
		marked.set(v);
	}

	protected boolean isMarked(int v) {
		checkBounds(v);
		return marked.get(v);
	}

	private void checkBounds(int v) {
		if (v < 0 || v > target.cardV()) {
			throw new IllegalArgumentException("check marked");
		}
	}

	public void compute() {	
		Iterator<Integer> order = getOrder();
		Stack<Integer> toVisit = new Stack<>();
		
		while (order.hasNext()) {
			int source = order.next();
			
			if (isMarked(source)) {
				continue;
			}
			
			toVisit.push(source);
			
			
			while (!toVisit.isEmpty()) {
				Integer current = toVisit.peek();
				mark(current);
				handleVertexEnd(current);
				
				boolean finished = true;
				for (Integer next : target.adj(current)) {
					if (!isMarked(next)) {
						toVisit.push(next);
						finished = false;
						break;
					}
				}
				if (finished) {
					toVisit.pop();
					handleFinished(current);
				}
			}
			handleSourceEnd(source);
		}
		
		
	}


	protected Iterator<Integer> getOrder() {
	    return new Iterator<Integer>() {
	    	private int counter = -1;
	    	
			@Override
            public boolean hasNext() {
	            return counter < target.cardV() - 1;
            }

			@Override
            public Integer next() {
				counter++;
	            return counter;
            }	
	    };
    }
	
	
	//handles when alg finishes considering one source
	protected void handleSourceEnd(int source) {
		   
    }

	//handle end of the vertex
	protected void handleVertexEnd(int next) {
	   
    }

	//handle when vertex is no longer reachable
	protected void handleFinished(int source) {
	  
    }
}
