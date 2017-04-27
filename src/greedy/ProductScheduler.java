package greedy;

import java.util.Iterator;

public class ProductScheduler extends GreedyScheduler {

	public double score(Job job) {
		return ((double) job.getWeight()) / job.getLength();
	}
	
	public ProductScheduler(Iterator<Job> it) {
		super(it);
	}
}
