package clustering.huge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import common.IntParser;

public class HugeReader extends IntParser {

	public HugeReader(String pathToFile) throws Exception {
	    super(pathToFile);
    }
	
	
	//costly operation
	public Set<Integer> getAllIntSet() {
		return new HashSet<Integer>(Arrays.asList(getAllInts()));
	}
	
	@Override
	public Integer[] getAllInts() {
		int arraySize = ints.get(0);						//size of the array
		int numberLength = ints.get(1);						// length of the number
		Integer[] result = new Integer[arraySize];
		
		for (int j = 0; j < result.length; j++) {
			result[j] = 0;
        }
		
		for (int i = 0; i < arraySize; i++) {
			for (int j = 0; j < numberLength; j++) {
				result[i] += ints.get(i * numberLength + j + 2) << (numberLength - 1 - j);
				assert(result[i] >= 0);
			}
		}
		return result;
	}

}
