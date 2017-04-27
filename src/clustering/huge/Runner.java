package clustering.huge;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import unionfind.UnionFind;


public class Runner {
	
	private static final int BASE = 24;
	
//	private static int hamming(int from, int to) {
//	return Integer.bitCount(from ^ to);
//	}

	
	private static final int flipBit(int target, int bitNo) {
		return target ^ (1 << bitNo);
	}
	
	private static final List<Integer> neighbors(Integer target) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < BASE; i++) {
			int oneNeigbor = flipBit(target, i);
			l.add(oneNeigbor);
			for (int j = i + 1; j < BASE; j++) {
				l.add(flipBit(oneNeigbor, j)); 
			}
		}
		return l;
	}
	
	
	public static void main(String... args) throws Exception {
		
		//user interaction xD
		if (args.length != 2) {
			System.err.println("Please supply the output text file");
			return;
		}
		
		//dirty job
		File output = new File(args[1]);
		
		if (!output.exists()) {
			output.createNewFile();
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(output));

		
		// be quick and dirty !!!
		Set<Integer> vertexes = new HugeReader(args[0]).getAllIntSet();
		Map<Integer, Integer> toConsecutive = new HashMap<>();
		
		// map vertexes to consecutive elements
		int k = 0;
		for (Integer vertex : vertexes) {
			toConsecutive.put(vertex, k++);
		}
		
		UnionFind uf = new UnionFind(vertexes.size());
	
		
		// union the given vertex with all its 2-3 neighbors
		for (Integer vertex : vertexes) {
			for (int neighbor : neighbors(vertex)) {
				if (vertexes.contains(neighbor)) {
					uf.union(toConsecutive.get(vertex), toConsecutive.get(neighbor));
				}
			}
		}
	
		//Yes, we just hard-hard code it
		System.out.println("Number of clusters" + uf.classes());
		
		//write the output to the specified text file
		out.write("No of clusters: " + uf.classes());
		
		out.close();
	}
}
