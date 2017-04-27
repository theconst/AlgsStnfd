package clustering;

import graph.GraphReader;
import graph.UndirectedGraph;

public class ClusteringReader extends GraphReader {

	
	public ClusteringReader(String pathToFile) throws Exception {
	    super(pathToFile);
    }

	public UndirectedGraph getGraph() {
		int cardV = ints.get(0);
		int cardE = ints.get(1);
		UndirectedGraph g = new UndirectedGraph(cardV, cardE);
		
		for (int i = 1; i < ints.size(); i += 3) {
			g.addEdge(
					ints.get(i),
					ints.get(i + 1), 
					ints.get(i + 2));
		}
		
		return g;
	}
}
