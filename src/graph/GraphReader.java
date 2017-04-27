package graph;

public class GraphReader extends common.IntParser {

	public GraphReader(String pathToFile) throws Exception {
		super(pathToFile);
	}
	
	
	public UndirectedGraph getGraph() {
		int cardV = ints.get(0);
		int cardE = ints.get(1);
		UndirectedGraph g = new UndirectedGraph(cardV, cardE);
		
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
