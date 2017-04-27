package quicksort;

public class MedianSorter<T extends Comparable<T>> extends Sorter<T> {
	
	@Override
	protected int choose(T[] a, int from, int to) {
		int mid = from + (to - from - 1) / 2;			//-1 in the second term is the convention adopted by the test
		
		return meadianOfThree(a, from, mid, to - 1);
	}
	
	private int min(T[] a, int first, int second) {
		return (a[first].compareTo(a[second]) <= 0) ? first : second;
	}
	
	private int max(T[] a, int first, int second) {
		return (a[first].compareTo(a[second]) >= 0) ? first : second;
	}
	
	
	//return index of the median of three elements of the array
	private int meadianOfThree(T[] a, int first, int second, int third) {
		 return max(a, min(a, first, second), 
				 	   min(a, max(a, first, second), third));
	}
}
