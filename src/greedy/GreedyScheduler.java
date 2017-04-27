package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

abstract public class GreedyScheduler extends Scheduler {
	
	private List<Job> schedule;
	
	
	public GreedyScheduler(Iterator<Job> it) {
		schedule = new ArrayList<Job>();
		while (it.hasNext()) {
			schedule.add(it.next());
		}
		
		schedule.sort(new Comparator<Job>() {
			@Override
			public int compare(Job first, Job second) {	
				return scheduleCompare(first, second);
			}
		});
	}
	
	@Override
	public Iterator<Job> schedule() {
		return schedule.iterator();
	}
	
	private int scheduleCompare(Job first, Job second) {
		int cmp = Double.compare(score(second), score(first));
		if (cmp == 0) {
			return second.getWeight() - first.getWeight();		//break ties
		}
		return cmp;
	}
	
	abstract public double score(Job job);
}
