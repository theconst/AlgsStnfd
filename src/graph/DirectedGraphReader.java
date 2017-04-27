package graph;

public class DirectedGraphReader extends common.IntParser {

	public DirectedGraphReader(String pathToFile) throws Exception {
		super(pathToFile);
	}
	
	
	public DirectedGraph getGraph() {
		int cardV = ints.get(0);
		int cardE = ints.get(1);
		DirectedGraph g = new DirectedGraph(cardV, cardE);
		
		for (int i = 2; i < ints.size(); i += 3) {
			g.addEdge(
					//change labeling
					ints.get(i) - 1,
					ints.get(i + 1) - 1, 
					ints.get(i + 2));
		}
		
		return g;
	}
}