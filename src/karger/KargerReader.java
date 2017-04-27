package karger;

import graph.UndirectedGraph;

import java.io.File;
import java.util.Scanner;


/**
 * Outputs the graph with all edge weights equal to one
 * read according to the adjacency lists
 * 
 * @author Home
 */
public class KargerReader {
	
	private static final String SEPARATOR = "\t| ";
	
	private UndirectedGraph graph = new UndirectedGraph();
	
	public KargerReader(String path) throws Exception {
		try (Scanner scanner = new Scanner(new File(path))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				String[] adjacencyList = line.split(SEPARATOR);
					
				//line contains the id in the first place;
				int fromId = Integer.parseInt(adjacencyList[0]) - 1;
			
				for (int i = 1; i < adjacencyList.length; ++i) {
					String s = adjacencyList[i];
					int toId = Integer.parseInt(s) - 1;
					graph.addEdge(fromId, toId, 1.0);
				}
			}
		} catch (Exception ex) {
			throw ex;			// be brutal
		}
	}
	
	public UndirectedGraph getGraph() {
		return graph;
	}

}
