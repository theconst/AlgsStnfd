package scc;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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
		
		//output file in append mode
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output, true));
		GreedyGraph target = new SCCReader(inputFile).getGraph();
		
	//	out.write("Input ");
	//	out.write(target.toString());
	//	out.write(System.lineSeparator());
		
		KosarajuSCC scc = new KosarajuSCC(target);
		scc.compute();
		
		
		int[] sizes = scc.sizes();
		
		Arrays.sort(sizes);

		System.out.println(sizes.length);
		for (int i = sizes.length - 1; i >= sizes.length - 5; i--) {
			System.out.println(sizes[i]);
		}
		
	//	out.write(apsp.toString());
	//	out.write(System.lineSeparator());
		out.close();
		
		
		System.out.printf("Successful from file %s output to %s", inputFile, outputFile);
	}
}
