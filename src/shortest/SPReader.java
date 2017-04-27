package shortest;

import graph.DirectedGraph;

import java.io.File;
import java.util.Scanner;

/* Reads the adjacency list according to the pattern */
public class SPReader {

	private static final String SEPARATOR = "\t| ";
	
	private static final String PAIR_SEPARATOR = ",";
	
	private DirectedGraph graph = new DirectedGraph();
	
	public SPReader(String path) throws Exception {
		try (Scanner scanner = new Scanner(new File(path))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] adjacencyList = line.split(SEPARATOR);
					
				//line contains the id in the first place;
				int fromId = Integer.parseInt(adjacencyList[0]) - 1;
				
				for (int i = 1; i < adjacencyList.length; ++i) {
					String[] toIdWeightPair = adjacencyList[i].split(PAIR_SEPARATOR);
					int toId = Integer.parseInt(toIdWeightPair[0]) - 1;
					double weight = Integer.parseInt(toIdWeightPair[1]);
					graph.addEdge(fromId, toId, weight);
				}
			}
		} catch (Exception ex) {
			throw ex;			// be brutal
		}
	}
	
	public DirectedGraph getGraph() {
		return graph;
	}

}
