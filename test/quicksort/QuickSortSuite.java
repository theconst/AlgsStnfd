package quicksort;

import static org.junit.Assert.*;

import org.junit.Test;

///!! the suite has fatal error - no before , so the arrays are never suited well
public class QuickSortSuite {
	private Integer[] reverseArray = new Integer[] {
			9, 8, 7, 6, 5, 4, 3, 2, 1, 0
	};
	
	private Integer[] inorderArray = new Integer[] {
			0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	};
	

	private Integer[] smallDummy = new Integer[] {2, 4, 1, 5, 3};
	
	private Sorter<Integer> alwaysFirst = new AlwaysFirstSorter<>();
	
	private Sorter<Integer> alwaysLast = new AlwaysLastSorter<>();
	
	private Sorter<Integer> median = new MedianSorter<>();
	
	
	
	@Test
	public void testSelectFirstOnReverseArray() {
		int n = reverseArray.length;
		
		alwaysFirst.quickSort(reverseArray);
		assertEquals("test the corner case", ((n - 1) * n) / 2,
				alwaysFirst.getNumberOfComparisons());
	}
	
	@Test
	public void testSelectFirstOnInorderArray() {	
		int n = inorderArray.length;
		
		alwaysFirst.quickSort(inorderArray);	
		assertEquals("test the corner case", ((n - 1) * n) / 2, 
				alwaysFirst.getNumberOfComparisons());
	}
	
	@Test
	public void testSelectFirstOnSmallArray() {
		alwaysFirst.quickSort(smallDummy);
		assertEquals("test the dummy case", 4 + 2, alwaysFirst.getNumberOfComparisons());
	}
	
	@Test 
	public void testSelectLastOnInorderArray() {
		int n = inorderArray.length;
		
		alwaysLast.quickSort(inorderArray);
		assertEquals("test the corner case", n * (n - 1) / 2, alwaysLast.getNumberOfComparisons());
	}
	
	@Test
	public void testSelectLastOnSmallArray() {
		alwaysLast.quickSort(smallDummy);
		assertEquals("test the dummy case", 4 + 1 + 1, alwaysLast.getNumberOfComparisons());
		
	}
	
	@Test
	public void testSelectMedianOnInorder() {
		median.quickSort(inorderArray);
		assertEquals("test the best case", 9 + 4 + 4 + 1 + 1, median.getNumberOfComparisons());
	}
	
	
	@Test
	public void testMedian() {
		int[] a = new int[] {
				8, 2, 4, 5, 7, 1	
		};
		
		int mid = a[0 + (a.length - 1) / 2 ];
		assertEquals("test the mid element", 4, mid);
	}
}
