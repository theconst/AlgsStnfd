package mst;

import graph.GraphReader;
import graph.UndirectedGraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Runner {

	public static void main(String... args) throws Exception {
		if (args.length != 2) {
			System.err.println("[input file] [output file]");
			return;
		}
	
		//dirty job
		String outputFile = args[1];
		String inputFile = args[0];
		File output = new File(outputFile);
			
		if (!output.exists()) {
			output.createNewFile();
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output));
		UndirectedGraph target = new GraphReader(inputFile).getGraph();
		
		out.write("Input ");
		out.write(target.toString());
		out.write(System.lineSeparator());
		
		//compute spanning tree
		MST mst = new KruskalMST(target);
		
		out.write("Spanning ");
		out.write(mst.toString());
		out.write(System.lineSeparator());
		out.write("Cost: ");
		out.write(Double.toString(mst.cost()));
		out.close();
		
		System.out.printf("Successful from file %s output to %s", inputFile, outputFile);
	}
}
