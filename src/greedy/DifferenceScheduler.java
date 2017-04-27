package greedy;

import java.util.Iterator;

public class DifferenceScheduler extends GreedyScheduler {
	
	public DifferenceScheduler(Iterator<Job> it) {
		super(it);
	}
	
	public double score(Job job) {
		return job.getWeight() - job.getLength();
	}
}
