package clustering;

import mst.KruskalMST;
import mst.MST;
import graph.UndirectedGraph;
import graph.WeightedEdge;


//never - ever define algorithm in the constructor


/**
 * Override version of the KruskalMST with stopping condition implemented as 
 * reached number of clusters
 * 
 * @author Home
 */
public class Clustering {
	
	protected int desiredNumberOfClusters = 2;									//default value
	
	protected double spacing = Double.POSITIVE_INFINITY;
	
	protected MST mst;
	
	
	// class that overrides the mst behavior
	private class MSTWrapper extends KruskalMST {

		public MSTWrapper(UndirectedGraph target) {
	        super(target);
	        
	        
	        /* compute spacing beetween nodes */
	        while (!unspannedEdges.isEmpty()) {
		    	WeightedEdge min = unspannedEdges.poll();
				
				if (!closesCycle(min)) {
					union(min.from(), min.to());
					
					/* add self-clustered vertices */
					this.mst.addVertex(min.from());
					this.mst.addVertex(min.to());
					
					/* select minimum */
					spacing = spacing > min.weight()? min.weight() : spacing;
				}
		    }
        }
		
		@Override
		protected boolean isFinished(UndirectedGraph target) {
			return (spannedVertexes.classes() == desiredNumberOfClusters);
		}
		
	}
	

	public Clustering(UndirectedGraph target, int numberOfClusters) {
	   this.desiredNumberOfClusters = numberOfClusters;
	   this.mst = new MSTWrapper(target);
    }
	
	public double spacing() {
		return spacing;
	}
	
	public UndirectedGraph getMST() {
		return mst.getMST();
	}

}
