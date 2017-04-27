package shortest;

import graph.DirectedGraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SingeSourceRunner {

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
		DirectedGraph target = new SPReader(inputFile).getGraph();
		
		out.write("Input ");
		out.write(target.toString());
		out.write(System.lineSeparator());
		
		//compute shortest tree
		AbstractSP sp = new BellmanFordSP(target);
		
		sp.findShortestPathFrom(0);
		
		out.write(sp.toString());
		out.write(System.lineSeparator());
		out.close();
		
		for (int i : Arrays.stream(new int[] {7,37,59,82,99,115,133,165,188,197}).map((x) -> x-1).toArray()) {
			System.out.printf("%s,", (int) sp.getDistTo(i));
		}
		
		
		System.out.printf("Successful from file %s output to %s", inputFile, outputFile);
	}
}
