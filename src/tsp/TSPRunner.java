package tsp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class TSPRunner {

	// no other source than zero!!!
	
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
		Roadmap target = new RoadmapReader(inputFile).getRoadmap();
		
		out.write("Input ");
		out.write(target.toString());
		out.write(System.lineSeparator());
		
		//compute spanning tree
		TSPFinder finder = new TSPFinder(target);
		
		finder.solve();
		
		out.write("Output ");
		
		out.write("Cost: ");
		out.write(Double.toString(finder.getTourWeight(target.size()-1)));
		out.close();
		
		
		System.out.println(Double.toString(finder.getTourWeight(target.size()-1)));
		System.out.println(Double.toString(finder.getCycleWeight()));
		System.out.printf("Successful from file %s output to %s", inputFile, outputFile);
		
	}
}
