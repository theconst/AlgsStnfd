package karger;

public class DeterministicGenerator implements IntegerGenerator {
	
	int cursor = 0;
	int[] generated;
	
	public DeterministicGenerator(int from, int to) {
		generated = new int[to - from];
		
		for (int i = 0; i < to; ++i) {
			generated[i] = i; 
		}
	}
	
	public DeterministicGenerator(int[] sequence) {
		generated = new int[sequence.length];
		
		System.arraycopy(sequence, 0, generated, 0, sequence.length);
	}

	@Override
    public boolean hasNextInt() {
	    return cursor < generated.length;
    }

	@Override
    public int getNextInt() {
	    if (cursor >= generated.length) {
	    	return -1;
	    }
	    return generated[cursor++];
    }

	@Override 
	public void reset() {
		cursor = 0;
	}
}
