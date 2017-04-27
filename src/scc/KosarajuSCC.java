package scc;

import java.util.Iterator;
import java.util.LinkedList;


public class KosarajuSCC {

	private GreedyGraph target;
	
	private int[] scc;
	
	private int sccCount = 0;
	
	
	public KosarajuSCC(GreedyGraph target) {
		this.target = target;
		this.scc = new int[target.cardV()];
	}
	
	public void compute() {
		GreedyGraph reverse = target.reverse();
		LinkedList<Integer> reverseOrder = new LinkedList<>();
		
		DepthFirstSearch reverseSearch = new DepthFirstSearch(reverse) {
			
			@Override
			protected void handleFinished(int source) {
				reverseOrder.addFirst(source);
			}
		};
		
		reverseSearch.compute();
		
		DepthFirstSearch forward = new DepthFirstSearch(target) {
			
			private int sccID = 0;
			
			@Override
			protected void handleSourceEnd(int source) {
				sccID++;
				sccCount = sccID;
			}
			
			@Override
			protected void handleVertexEnd(int vertex) {
				scc[vertex] = sccID;
			}
			
			@Override
			protected Iterator<Integer> getOrder() {
				return reverseOrder.iterator();
			}
		};
		
		forward.compute();
	}
	
	
	public int[] scc() {
		return scc;
	}
	
	public int[] sizes() {
		int[] result = new int[sccCount];
		
		for (int i = 0; i < scc.length; i++) {
			result[scc[i]]++; 
		}
		return result;
	}
	
	
	
}
