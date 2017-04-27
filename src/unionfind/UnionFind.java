package unionfind;


/**
 * The union-find data structure representing the union-find structure
 * 
 * @author Home
 */
public class UnionFind {
	
	/**
	 * parent of the element
	 * if parent[i] = i, then i is the representative of the equivalence class
	 */
	private int[] parent;						
						
	/**
	 * Rank of the element - max number of hops to reach the node
	 */
	private int[] rank;
	
	/**
	 * number of equivalence classes
	 */
	private int classes;			
	
	int rank(int i) {										//for testing only
		if (i < 0 || i > rank.length) {
			return -1;
		}
		return rank[i];
	}
	
	int depth(int x) {										//for testing only
		if (x < 0 || x > parent.length) {
			return -1;
		}
		
		int depth = 0;
		int i = x;
		while(parent[i] != i) {
			i = parent[i];
			depth++;
		}
		return depth;
	}

	
	public UnionFind(int cardinality) {
		parent = new int[cardinality];
		rank = new int[cardinality];
		classes = cardinality;
		
		for (int i = 0; i < cardinality; ++i) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	
	public int find(int x) {
		if (x < 0 || x >= parent.length) {
			return -1;
		}
		
		int i = x;
		
		
		compressPath(x, i);										//optimized path compression
		
		
		while(parent[i] != i) {
		//	parent[i] = parent[parent[i]];						//path compression
			i = parent[i];
		}
		
		
		return i;
	}
	
	private void compressPath(int from, int root) {
		while (parent[from] != root) {
			int next = parent[from];							//remember the next node
			parent[from] = root;								//set node pointer to the root
			from = next;										//move to the next node			
		}
	}
	
	public int union(int first, int second) {
		int firstClass = find(first);							// get the equivalence class of the first element
		int secondClass = find(second);							// get the equivalence class of the first element
		
		if (firstClass < 0 || secondClass < 0) {		
			throw new IllegalArgumentException();
		}
		
		if (firstClass == secondClass) {						//nothing to do if in the same class
			return firstClass;
		}
		
		int cmp = Integer.compare(rank[firstClass], 
				rank[secondClass]);
		if (cmp < 0) {											//element with smaller rank gets attached
			parent[firstClass] = secondClass;					
		} else if (cmp > 0) {
			parent[secondClass] = firstClass;
		} else {												//if elements have equal rank increase the rank
			rank[firstClass] += 1;								//rank of the root increases
			parent[secondClass] = firstClass;
		}
		classes--;												//decrease number of classes							
		return parent[firstClass];								//return new equivalence class	
	}
	
	public int classes() {
		return classes;
	}
	
	public boolean areEqueal(int x1, int x2) {					
		return find(x1) == find(x2);							//true if elements belong to the same class
	}	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("{");;
	
		for (int i = 0; i < parent.length - 1; ++i) {
			sb.append(i).append("->").append(parent[i]).append("; ");
		}
		
		return sb.append(parent.length - 1).append("->")
				.append(parent[parent.length - 1]).append("}").toString();
	}

}
