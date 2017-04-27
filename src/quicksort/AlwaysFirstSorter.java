package quicksort;

public class AlwaysFirstSorter<T extends Comparable<T>> extends Sorter<T> {
	
	@Override
	protected int choose(T[] a, int from, int to) {
		return from;
	}
}
