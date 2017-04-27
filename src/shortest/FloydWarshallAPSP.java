package shortest;

import graph.DirectedGraph;
import graph.WeightedEdge;

public class FloydWarshallAPSP extends AbstractAPSP {
	
	public FloydWarshallAPSP(DirectedGraph target) {
	    super(target);
    }

	@Override
    public void findShortestPaths() {
		int cardV = target.cardV();
		
		for (int i = 0; i < cardV; ++i) {
			for (WeightedEdge w : target.edges()) {
				edgeTo[w.to()][w.from()] = w.from();
				distTo[w.to()][w.from()] = w.weight();
			}
		}
		
		for (int k = 0; k < cardV; k++)  {										//for all possible intermediate vertices
			for (int i = 0; i < cardV; i++) {									//for all possible sources
				for (int j = 0; j < cardV; j++) {								//for all possible destinations
					double possibleDistance = distTo[i][k] + distTo[k][j]; 
					if (possibleDistance < distTo[i][j]) {
						distTo[i][j] = possibleDistance;
						edgeTo[i][j] = edgeTo[i][k];
					}
				}
			}
		}	
    }
}
