package karger;

import graph.UndirectedGraph;
//import graph.Vertex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
//import java.util.List;
//import java.util.Set;

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
		UndirectedGraph target = new KargerReader(inputFile).getGraph();
		
		out.write("Input ");
		out.write(target.toString());
		out.write(System.lineSeparator());
		
		//compute minimum cut
		KargerMinCut minCut = new KargerMinCut(target, 10000, "random");
		minCut.computeCut();
		
		/*
		List<KargerMinCut.Pair<Set<Vertex>, Set<Vertex>>> cuts = minCut.getCuts();
		
		int minSize = Integer.MAX_VALUE;
		
		for (KargerMinCut.Pair<Set<Vertex>, Set<Vertex>> cut: cuts) {
			int size = 0;
			for (Vertex v: cut) {
				for (WeightedEdge we: target.adj(v)) {
					if (!cut.contains(we.to())) {
						firstSize += 1;
					}
				}
			}
			if (size < minSize) {
				minSize = size;
			}
		}
		*/
		/*
		System.out.println(minSize / 2);
		*/
		//out.write(minCut.toString());
		out.write(System.lineSeparator());
		
		out.close();
		
		System.out.printf("Successful from file %s output to %s", inputFile, outputFile);
	}
}

