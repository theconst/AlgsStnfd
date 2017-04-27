package karger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import unionfind.UnionFind;
import graph.UndirectedGraph;
import graph.WeightedEdge;


//TODO correct the alg- correct answer = 17
public class KargerMinCut {

	private List<Set<Integer>> cuts = null;
	
	private String genType;

	private UndirectedGraph graph;

	private int trials;
	
	public KargerMinCut(UndirectedGraph graph, int trials, String genType) {
		
		// graph is asserted to have vertexes numbered from 0 to n - 1
		this.graph = graph;
		this.trials = trials;
		this.genType = genType;
	}
	
	public void computeCut() {
		cuts = new ArrayList<Set<Integer>>(trials);
		
		WeightedEdge[] edges = (WeightedEdge[]) graph.edges().toArray(new WeightedEdge[graph.cardE()]);
		
		//TODO correct the bottleneck here
		IntegerGenerator gen = GeneratorFactory.createGenerator(genType, 0, edges.length);
		int cardV = graph.cardV();
		for (int trial = 0; trial < trials; ++trial) {
			Set<Integer> cut = new HashSet<>();
			Set<Integer> complementCut = new HashSet<>();
			UnionFind contracted = new UnionFind(cardV);
			
			while (contracted.classes() > 2) {
				WeightedEdge choosen = edges[gen.getNextInt()];
				contracted.union(choosen.from(), choosen.to());
			}
			
			// there are now only two classes
			int firstClass = contracted.find(0);
			
			for (Integer v: graph.vertexes()) {
				if (contracted.find(v) == firstClass) {
					cut.add(v);
				} else {
					complementCut.add(v);
				}
			}
	
			
			cuts.add(cut);
			gen.reset();															//reset the generator
		}
	}
	
	
	public List<Set<Integer>> getCuts() {
		if (cuts == null) {
			throw new IllegalStateException("Run the compute cut first");
		}		
		return cuts;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("MinCut, trials = ").append(trials).append(" {");
		sb.append(System.lineSeparator());
		
		int trialNo = 1;
		for (Set<Integer> cut: cuts) {
			sb.append(trialNo++).append(": ");
			List<Integer> complement = new ArrayList<Integer>();
			for (Integer v: graph.vertexes()) {
				if (cut.contains(v)) {
					sb.append(v.toString());
				} else {
					complement.add(v);
				}
			}
			sb.append("-");
			for (Integer v: complement) {
				sb.append(v);
			}
			sb.append(System.lineSeparator());
		}
		return sb.append("}").toString();
	}
}
