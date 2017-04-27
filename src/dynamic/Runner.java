package dynamic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
public class Runner {

	
public static void main(String... args) throws Exception {
		
		//user interaction xD
		if (args.length != 3) {
			System.err.println("[in][out][solver]");
			return;
		}
		
		//dirty job
		File output = new File(args[1]);
		
		if (!output.exists()) {
			output.createNewFile();
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output, true));

		
		// be quick and dirty !!!
		KnapsackProblem problem = new KnapsackReader(args[0]).getKnapsackSubproblem();
		
		
		AbstractKnapsackSolver solver = null;
		if ("straight".equalsIgnoreCase(args[2])) {
			solver = new StraightKnapsackSolver(problem);
		} else if ("cached".equalsIgnoreCase(args[2])) {
			solver = new CachedKnapsackSolver(problem);
		} else {
			System.err.println("specify the correct solver");
			System.exit(1);
		}
		
		solver.solve();

		System.out.println("Optimal value: " + solver.getSolutionValue());
		
		out.append("Optimal value for " + args[0] + " with solver " + args[2] + " " + solver.getSolutionValue());
		
		out.close();
	}
}
