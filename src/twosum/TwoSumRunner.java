package twosum;


import java.io.File;
import java.util.Scanner;

//! ineffective
public class TwoSumRunner {
	
	private static final int RANGE_LEN = 20001;
	private static final int LOW = -10000;
	private static final int HIGH = 10000;
	
	private static final int SIZ =  1000000;
	
	public static void main(String... args) throws Exception {
		if (args.length != 1) {
			System.err.println("[input file]");
			return;
		}
		String inputFile = args[0];
		
		//dirty shit
		Long[] inputs = new Long[SIZ];
		Long[] targets = new Long[RANGE_LEN];
		
		for(int i = 0, j = LOW; j <= HIGH; i++, j++) {
			targets[i] = (long) j;
		}
		
		int i = 0;
		try (Scanner scanner = new Scanner(new File(inputFile))) {
			//scanner.useDelimiter(System.lineSeparator());
			while(scanner.hasNextLong()) {
				inputs[i++] = scanner.nextLong();
			}
		} catch (Exception ex) {
			throw ex;			// be brutal
		}
		
		
		TwoSumFinder finder = new TwoSumFinder(inputs, targets);
		
		finder.findCount();
		
		int count = finder.getCount();
		
		System.out.printf("Number of sums: %d%n", count);
	}

}
