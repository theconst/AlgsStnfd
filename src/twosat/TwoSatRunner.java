package twosat;

import common.IntParser;

public class TwoSatRunner {
	
	
	public static void main(String... args) throws Exception {
		for (int no = 0; no < args.length; no++) {
			String inputFile = args[no];
			IntParser parser = new IntParser(inputFile);
			Integer[] clauses = parser.getAllInts();
			TwoSatSolver solver = new TwoSatSolver(clauses);
			
			System.out.print(solver.isSatisfiable() ? 1 : 0);
		}
		
	}
	
}
