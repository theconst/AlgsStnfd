package tsp;


import java.util.*;

//TODO remove bugs with source
//TODO implement using prev and next arrays

// source cannot be calculated other than 0!
public class TSPFinder {
	private static final double INF = Double.POSITIVE_INFINITY;
	
	private double[][] lengths;
//	private Map<Integer, Float>[] lengths;
	
	private Roadmap target;
	
	private int size;
	
	private int source;
	
	private int sourceSet;
	
	private int all;
	
    public TSPFinder(Roadmap map) {
		this.target = map;
		this.size = map.size();
		this.source = map.source();
		this.sourceSet = 1 << source;
		this.lengths = new double[size][1 << (this.size - 1)];
		this.all = (~0) >>> (Integer.BYTES * 8 - size);
		
//		this.lengths = new float[size][binomial(size + 1, size / 2)];
		
//		this.lengths = (Map<Integer, Float>[]) new TreeMap[size];
		
//		for (int i = 0; i < lengths.length; ++ i) {
//			lengths[i] = new TreeMap<>();
//		}
		
		for (int i = 0; i < lengths.length; ++i) {
			Arrays.fill(lengths[i], INF);
		}	
	}
	
	
	public void solve() {		
		setLength(source, sourceSet, 0.0f);
//		lengths[SOURCE][0] = 0.0f;														//base case
			
		for (int cardinality = 2; cardinality <= size; cardinality++) {
			List<Integer> subsets = combinationsWithOne(cardinality);
			
			for (int subset : subsets) {
				int[] elements = elems(subset);	
				for (int j : elements) {
					if (j != source) {
						int prevSubset = sub(subset, j);
						double min = INF;
						
						for (int k : elements) {
							if (k != j) {
								min = Math.min(min, getLength(k, prevSubset) + distance(k, j));
							}
						}
						setLength(j, subset, min);	
					}
				}	
			}
		}
	}
	
	public double getCycleWeight() {
		double min = INF;
		for (int last : elems(all)) {
			if (last == source) continue;
			min = Math.min(min, getLength(last, all) + distance(last, source));
		}
		return min;
	}

	
	public double getTourWeight(int to) {
		return getLength(to, all);
	}
	
	private int sub(int set, int index) {
		return MaskedSets.sub(set, index);
	}
	
	private int[] elems(int set) {
		return MaskedSets.elems(set);
	}
	
	private List<Integer> combinationsWithOne(int cardinality) {
		return MaskedSets.combinations(sourceSet, 0, size, cardinality);
	}
	
	private int indexOf(int set) {
		return set >>> 1;
//		return MaskedSets.orderOf(set);
	}
	
	private float distance(int k, int j) {
		return target.distance(k, j);
	}
	
//	private int binomial(int n, int k) {
//		return MaskedSets.binomial(n, k);
//	}
	
	private double getLength(int dest, int subset) {	
//		return lengths[dest].getOrDefault(indexOf(subset), INF);
		return lengths[dest][indexOf(subset)];
	}
	
	private void setLength(int dest, int subset, double value) {
		lengths[dest][indexOf(subset)] = value;
//		lengths[dest].put(indexOf(subset), value);
	}
	
}
