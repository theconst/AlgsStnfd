package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



/** Brute-force parser */
public class IntParser {
	
	protected ArrayList<Integer> ints = new ArrayList<>();
	
	public IntParser(String pathToFile) throws Exception {
		
		try (Scanner scanner = new Scanner(new File(pathToFile))) {
			//scanner.useDelimiter(System.lineSeparator());
			while(scanner.hasNextInt()) {
				ints.add(scanner.nextInt());
			}
		} catch (Exception ex) {
			throw ex;			// be brutal
		}
	}

	public int nextInt() {
		throw new UnsupportedOperationException("get all ints instead");
	}
	
	public Integer[] getAllInts() {
		return ints.toArray(new Integer[ints.size()]);
	}
	
}
