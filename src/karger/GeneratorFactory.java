package karger;


/// not a real factory
public class GeneratorFactory {
	
	public static IntegerGenerator createGenerator(String type, int from, int to) {
		if (type == "random") {
			return new RandomGenerator(from, to); 
		} else if (type == "deterministic") {
			return new DeterministicGenerator(from, to);
		} else {
			throw new IllegalArgumentException("No such generator");
		}
	}

}
