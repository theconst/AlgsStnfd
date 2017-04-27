package inversions;

import common.IntParser;


public class InversionCounter {
	
	private long inversionsCount = 0;
	
	private<T extends Comparable<T>> long count(T[] a, T[] aux, int from, int to) {
		
		if (to - from <= 1) {
			return 0;
		}
		
		int mid = from + (to - from) / 2;
		
		return merge(a, aux, 
					count(a, aux, from, mid), 
					count(a, aux, mid, to), 
				    from, mid, to);
	}
	
	private <T extends Comparable<T>>int binarySearch(T[] a, int from, int to, T what) {
		if (to - from <= 0) {
			return from;
		}
		
		int mid = from + (to - from) / 2;
		
		int cmp = a[mid].compareTo(what);
		
		if (cmp < 0) {		  // a[mid] is less than what
			return binarySearch(a, mid + 1, to, what);
		} else if (cmp > 0) { // a[mid] is greater than what
			return binarySearch(a, from, mid, what);
		} else {
			return mid;
		}
	}
	
	
	private<T extends Comparable<T>> long merge(T[] a, T[] aux, 
			long inversionsLeft, long inversionsRight, 
			int from, int mid, int to) {
		long inversions = 0;
		int left = from;
		int right = mid;
		int x = from;
		
		while ((left < mid) || (right < to)) {
			if ((right >= to) ? true : (left < mid) && less(a[left], a[right])) {
				aux[x++] = a[left++];
			} else {
				inversions += mid - binarySearch(a, from, mid, a[right]);
				aux[x++] = a[right++];
			}
		}
	
		System.arraycopy(aux, from, a, from, to - from);
		
		return inversionsLeft + inversionsRight + inversions;
	}
	
	private <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) <= 0;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> InversionCounter(T[] target) {
		inversionsCount = count(target, (T[]) new Comparable[target.length], 0, target.length);
	}
	
	public static <T extends Comparable<T>> long getCount(T[] target) {
		return new InversionCounter(target).inversionsCount;
	}
	
	public static void main(String... args) throws Exception {
		Integer[] ints = new IntParser(args[0]).getAllInts();
		
		// shaitan code - overflows integers !
		System.out.printf("Inversion in file  : %d\n", InversionCounter.getCount(ints));
		System.out.printf("Max value possible : %d\n", (long)ints.length * (ints.length - 1) / 2);
		
	}
	
	
}
