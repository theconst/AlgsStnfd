package karger;

public interface IntegerGenerator {
	boolean hasNextInt();
	int getNextInt();
	void reset();						//reset generator
}
