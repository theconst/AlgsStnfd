package scc;


public class SCCReader extends common.IntParser {

	public SCCReader(String pathToFile) throws Exception {
		super(pathToFile);
	}
	
	
	public GreedyGraph getGraph() {
		int cardV = 875714;			//!!! fucking hard
		
		GreedyGraph g = new GreedyGraph(cardV);
		
		for (int i = 0; i < ints.size(); i += 2) {
			g.addEdge(
					ints.get(i) - 1,
					ints.get(i + 1) - 1);
		}
		
		return g;
	}
}