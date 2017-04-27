package tsp;


import java.util.LinkedList;
import java.util.List;

public class MaskedSets {
	private static final int MAX_SIZE = Integer.BYTES * 8;

	private static List<Integer> combinations(int used, int resource, int from, int to, List<Integer> acc) {
		
		if (resource <= 0) {
			acc.add(used);
			return acc;
		}
		
		if (to - from >= resource) {
			combinations((1 << from) | used, resource - 1, from + 1, to, acc);
		}
		
		if (to > from) {
			combinations(used, resource, from + 1, to, acc);
		}
		
		return acc;
	}
	
	public static List<Integer> combinations(int used, int from, int to, int cardinality) {
		if ((from < 0) || (to <= 0) || (cardinality < 0)) {
			throw new IllegalArgumentException("The numbers should be positive");
		}
		
		if ((to > MAX_SIZE)) {
			throw new IllegalArgumentException("The numbers should not be greater than " + MAX_SIZE);
		}
		
		return combinations(used, cardinality, from, to, new LinkedList<Integer>());
	}
	
	public static List<Integer> combinations(int setCardinality, int subsetCardinality) {
		return combinations(0, 0, setCardinality, subsetCardinality);
	}
	
	public static int[] elems(int set) {
		int[] indexes = new int[Integer.bitCount(set)];
		
		int k = 0;
		for (int i = 0; (set >> i) != 0; i++) {
			if (contains(set, i)) {
				indexes[k++] = i;
			}
		}
		return indexes;
	}
	
	public static boolean contains(int set, int index) {
	    return ((set >>> index) & 1) == 1;
    }
	
	public static int sub(int set, int index) {
		return (set & ~(1 << index));
	}
	
	public static int orderOf(int combination) {
		int[] indexes = elems(combination);
		int result = 0;
		
		for (int i = 0; i < indexes.length; i++) {
			result += binomial(indexes[i], i);
		}
		
		return result;
	}
	
	public static int card(int set) {
		return Integer.bitCount(set);
	}
	
	//binomial (n >= k)
	public static int binomial(int n, int k) {
		
		if (n < k) {
			return 0;
		}
		
		if (k > n - k) {
			return binomial(n, n - k);
		}
		
		int coefficient = 1;
		for (int i = 1, m = n; i <= k; i++, m--) {
            coefficient = coefficient * m / i;
		}
        return coefficient;
    }

	public static void main(String... args) {
		
//		System.out.println("Subsets of three set");
//		System.out.println(Arrays.toString(combinations(3,2).stream().map(x -> Integer.toBinaryString (x)).toArray()));
//		System.out.println("Subsets of four sets");
//		System.out.println(Arrays.toString(combinations(4,2).stream().map(x -> Integer.toBinaryString (x)).toArray()));
//		System.out.println("Subsets of five sets");
//		System.out.println(Arrays.toString(combinations(5,2).stream().map(x -> Integer.toBinaryString (x)).toArray()));
//		System.out.println(combinations(15, 7).size());
//		
//		System.out.println(contains(8, 3));
//		System.out.println(contains(8, 2));
//		System.out.println(contains(8 + 16, 4));
//		System.out.println(Integer.toBinaryString(sub(8 + 16, 3)));
//		
//		System.out.println(Arrays.toString(combinations(1, 1, 4, 2).stream().map(x -> Integer.toBinaryString (x)).toArray()));
		
//		System.out.println(binomial(3, 2));
//		System.out.println(binomial(2, 3));
//		
//		System.out.println(binomial(24, 0));
		
		for (int i : new int[] {9, 12, 10, 6, 5, 3}) {
			System.out.println(orderOf(i));
		}
		
	}
	
}
