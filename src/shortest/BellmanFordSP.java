package shortest;

import graph.DirectedGraph;
import graph.WeightedEdge;

public class BellmanFordSP extends AbstractSP {
	
	public BellmanFordSP(DirectedGraph target) {
	    super(target);
    }

	@Override
    protected void handleSP() {
		int cardV = target.cardV();
		
		for (int i = 0; i < cardV; ++i) {
			for(int j = 0; j < cardV; ++j) {
				relax(j);
			}
		}
		
		verifySolution();
    }

	public void verifySolution() {
	    for (int i = 0; i < target.cardV(); ++i) {
			for (WeightedEdge we : target.adj(i)) {
				int from = we.from();
				int to = we.to();
				if (distTo[to] > distTo[from] + we.weight()) {
					throw new IllegalArgumentException("Graph has negative cycles");
				}
			}
		}
    }

	
}
