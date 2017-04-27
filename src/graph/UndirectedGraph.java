package graph;

/**
 * This is simple graph with simple adding methods and needed accessing methods
 * (no deleting - just constructing)
 * 
 * @author Koshak
 */
public class UndirectedGraph extends AbstractGraph {
	
	//"inherit" constructors
	public UndirectedGraph() {
		super(1, 1);
	}
	
	public UndirectedGraph(int cardV, int cardE) {
		super(cardV, cardE);
	}
	
	/* the add edge adds both to-> from and from->to to the implementation */
	@Override
	public void addEdge(WeightedEdge edge) {
		super.addEdge(edge);
		edges.get(edge.to()).add(new WeightedEdge(edge.to(), edge.from(), edge.weight()));
	}
	
	@Override
	public int cardE() {
		return super.cardE() / 2;
	}
}
