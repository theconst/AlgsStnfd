package median;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import common.IntParser;


public class MedianMaintainerRunner {
	
	private static final int MODULO = 10000;

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
		
		
		IntParser parser = new IntParser(inputFile);
	
		Integer[] sequence = parser.getAllInts();
		
		MedianMaintainer maintainer = new MedianMaintainer(sequence);
		
		int[] result = maintainer.getMedianByStep();
		
		out.write("Result:");
		out.write(System.lineSeparator());
		out.write(Arrays.toString(result));
		out.write(System.lineSeparator());
		
		int sumMod10000 = Arrays.stream(result).reduce((x, y) -> (x + y) % MODULO).getAsInt(); 
		out.write("SUM " + sumMod10000);
		
		out.close();
	}
}
