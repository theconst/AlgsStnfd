package shortest;

import graph.DirectedGraph;
import graph.WeightedEdge;

public class JohnsonsAPSP extends AbstractAPSP {
	
	/*
	 * Proof is the fact that reweighting causing 
	 * the increase in weight on exactly the same value for each path
	 */

	public JohnsonsAPSP(DirectedGraph target) {
	    super(target);
    }

	@Override
    public void findShortestPaths() {
		
		//initialize solvers
	    BellmanFordSP bellmanSP = new BellmanFordSP(target);
	    DijkstraSP dijkstraSP = new DijkstraSP(target) {
	    	
	    	@Override
	    	protected void relax(WeightedEdge e) {
	    		double reweighted = e.weight() + bellmanSP.getDistTo(e.from()) - bellmanSP.getDistTo(e.to());	
	    		
	    		//original weight of target is not modified
	    		WeightedEdge reweightedEdge = new WeightedEdge(e.from(), e.to(), reweighted);
	    		
	    		super.relax(reweightedEdge);
	    	}
	    };
	    
	
	    int cardV = target.cardV();
	    
	    //find shortest paths with bellman-fordSP
	    bellmanSP.findShortestPathFrom(0);
	        
		for (int i = 0; i < cardV; ++i) {
	    	edgeTo[0][i] = bellmanSP.getEdgeTo(i);
	    	distTo[0][i] = bellmanSP.getDistTo(i);
	    }
	    
		//find shortest path with Dijkstra
	    for (int from = 1; from < cardV; ++from) {
	    	dijkstraSP.findShortestPathFrom(from);
	    	for (int to = 0; to < cardV; ++to) {
	    		edgeTo[from][to] = dijkstraSP.getEdgeTo(to);
	    		
	    		//reweight back
	    		distTo[from][to] = dijkstraSP.getDistTo(to) + bellmanSP.getDistTo(to) - bellmanSP.getDistTo(from);
	    	}
	    }
	    
    }

	
	
	
}
