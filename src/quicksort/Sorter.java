package quicksort;

public class Sorter<T extends Comparable<T>> {
	
	protected long numberOfComparisons = 0;
	
	public void quickSort(T[] a) {
		numberOfComparisons = 0;			//initialize the number of comparisons to 0
		quickSort(a, 0, a.length);
	}
	
	private void quickSort(T[] a, int from, int to) {
		if (to - from <= 1) {
			return;
		}
		
		int j = partition(a, from, to);
		
		numberOfComparisons += (j - from);	//length of the array - 1
		quickSort(a, from, j);
		
		numberOfComparisons += (to - (j + 1));		//length of the array - 1
		quickSort(a, j + 1, to);
	}
	
	protected int choose(T[] a, int from, int to) {
		return from + (to - from) / 2;
	}
	
	/* Another way to implement
	private int partition(T[] a, int from, int to) {
		int pivot = choose(a, from, to); //from + (to - from) / 2
		int left = from;
		int right = to;
		
		swap(a, from, pivot);	//T temp = a[first]; a[first] = a[second]; a[second] = temp;
		
		while (true) {
			while (++left < to && less(a[left], a[from]));
			while (!less(a[--right], a[from]));
			if (left >= right) {
				break;			// the place for pivoting argument was found
			}
			swap(a, left, right); //place the pivoting argument
		}
		swap(a, from, right);
		return right;
	}
	*/
	
	private int partition(T[] a, int from, int to) {
		int pivot = choose(a, from, to);
		int gtoe;							// GreaTer Or Equal (points to end of the values greater/equal than pivot)
		int ls;								// LeSs (points to end of the array less than pivot)
		swap(a, from, pivot);				// put the pivot element to the a[from]
		
		for (gtoe = from + 1, ls = from; gtoe < to; gtoe++) {
			if (!less(a[from], a[gtoe])) {
				swap(a, ++ls, gtoe);
			}
		}
		swap(a, from, ls);
		return ls;
	}
	
	/* helper methods */
	
	protected boolean less(T a, T b) {
		return a.compareTo(b) <= 0;
	}
	
	private void swap(T[] a, int first, int second) {
		T temp = a[first];
		a[first] = a[second];
		a[second] = temp;
	}
	
	public long getNumberOfComparisons() {
		return numberOfComparisons;
	}
	
	public static void main(String... args) {
		Sorter<Integer> s = new Sorter<>();
		
		Integer[] a = new Integer[] {
			1, 3, 4, 5, 6 , 7, 8, 9, 1, 1, 1, 2, 3, 4, 5, 6, 8, 1, 2, 3, 4, 6, 7, 8, 9, 9, 4, 3, 3, 2, 1, 2, 3, 3
		};
		
		s.quickSort(a);
		
		for (Integer q: a) {
			System.out.printf("%d ", q);
		}
	}
}
