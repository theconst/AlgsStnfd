package clustering;

import graph.UndirectedGraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Runner {
	
	public static final int NUMBER_OF_CLUSTERS = 4;
	
	/**
	 * Quick and dirty runner for the application
	 * 
	 * @param args		 [input text file] [output text file]
	 * @throws Exception 
	 */
	public static void main(String... args) throws Exception {
		
		//user interaction xD
		if (args.length != 2) {
			System.err.println("Please supply the output text file");
			return;
		}
		
		//dirty job
		File output = new File(args[1]);
		
		if (!output.exists()) {
			output.createNewFile();
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output));
		
		
		//read graph from the input
		UndirectedGraph graph = new ClusteringReader(args[0]).getGraph();
		
		//perform k - clustering
		Clustering clustering = new Clustering(graph, NUMBER_OF_CLUSTERS);
		UndirectedGraph clusters = clustering.getMST();
		
		//write the output to the specified text file
		out.write("Clustering " + clusters.toString() + System.lineSeparator());
		out.write("Spacing: " + clustering.spacing() + System.lineSeparator());
		
		//flush and close
		out.close();
	}

}
