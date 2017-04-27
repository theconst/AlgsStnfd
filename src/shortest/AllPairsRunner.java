package shortest;

import graph.DirectedGraph;
import graph.DirectedGraphReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class AllPairsRunner {
	
	private static double getShortestShortestPath (AbstractAPSP sp, int cardV) {
		assert(cardV >= 2);
		double min = sp.getDistanceTo(0, 1);
		for (int i = 0; i < cardV; i++) {
			for (int j = 0; j < cardV; j++) {
				if (i != j) {
					min = Math.min(sp.getDistanceTo(i, j), min);
				}
			}
		}
		return min;
	}
	
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
		
		//output file in append mode
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output, true));
		DirectedGraph target = new DirectedGraphReader(inputFile).getGraph();
		
		out.write("Input ");
		out.write(target.toString());
		out.write(System.lineSeparator());
		
		AbstractAPSP apsp = new JohnsonsAPSP(target);
		apsp.compute();
		double shortestShortest = getShortestShortestPath(apsp, target.cardV());
		
		System.out.println(shortestShortest);
		
	//	out.write(apsp.toString());
	//	out.write(System.lineSeparator());
		out.close();
		
		
		System.out.printf("Successful from file %s output to %s", inputFile, outputFile);
	}
	
	
}
