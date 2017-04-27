package quicksort;

public class AlwaysLastSorter<T extends Comparable<T>> extends Sorter<T> {
	
	@Override
	protected int choose(T[] a, int from, int to) {
		return to - 1;
	}
}
