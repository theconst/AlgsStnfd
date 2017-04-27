package quicksort;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import common.IntParser;

public class Runner {
	
	/**
	 * Quick and dirty runner for the application
	 * 
	 * @param args		 [input text file] [output text file]
	 * @throws Exception 
	 */
	public static void main(String... args) throws Exception {
		
		//user interaction xD
		if (args.length != 2) {
			System.err.println("Please supply the output text file");
			return;
		}
		
		//dirty job
		File output = new File(args[1]);
		
		if (!output.exists()) {
			output.createNewFile();
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output));
		
		
		//read ints from the input
		Integer[] ints = new IntParser(args[0]).getAllInts();
		Integer[] intsCopy = new Integer[ints.length];
		
		//Create sorters
		Sorter<Integer> alwaysFirst = new AlwaysFirstSorter<>();
		Sorter<Integer> alwaysLast = new AlwaysLastSorter<>();
		Sorter<Integer> median = new MedianSorter<>();
		
		//sort copy
		System.arraycopy(ints, 0, intsCopy, 0, ints.length);
		alwaysFirst.quickSort(intsCopy);
		long alwaysFirstComparisons = alwaysFirst.getNumberOfComparisons();
		
		//sort copy
		System.arraycopy(ints, 0, intsCopy, 0, ints.length);
		alwaysLast.quickSort(intsCopy);
		long alwaysLastComparisons = alwaysLast.getNumberOfComparisons();
		
		//sort original, because no longer needed
		median.quickSort(ints);
		long medianComparisons = median.getNumberOfComparisons();
		
		//write the output to the specified text file
		out.write("Always first n of comparisons: " + alwaysFirstComparisons + System.lineSeparator());
		out.write("Always last n of comparisons: " + alwaysLastComparisons + System.lineSeparator());
		out.write("Median of three n of comparisons: " + medianComparisons + System.lineSeparator());
		
		
		//flush and close
		out.close();
	}
}
