package karger;

import java.util.Random;

public class RandomGenerator implements IntegerGenerator {
	
	private int cursor;
	private int[] generated;
	private int from;
	
	@SuppressWarnings("unused")
    private int to;											//maybe useful in further modification
	
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public void init() {
		long seed = System.currentTimeMillis() % 65535;
		Random generator = new Random(seed);

		for (int i = 0; i < generated.length; ++i) {
			generated[i] = from + i;
		}
		
		for (int i = generated.length - 1; i > 0; i--) {
			int choosen = generator.nextInt(i + 1);
			swap(generated, i, choosen); 
		}
		cursor = 0;
	}
	
	/*
	 * Creates a random generator with integer [from, to)
	 * using Knuth shuffling
	 */
	public RandomGenerator(int from, int to) {
		assert(from >= 0);
		assert(to >= 0);
		
		this.from = from;
		this.to = to;
		generated = new int[to - from];
		init();
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
		init();														//reinitialize the array
	}

}
