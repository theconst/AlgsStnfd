package greedy;

import java.util.Iterator;

abstract public class Scheduler {
	
	/**
	 * Returns the schedule of the jobs
	 * 
	 * result[i] = k <=> job of with id = k is in i_th - 1 (enumeration from zero) place (*)
	 * 
	 * @param jobs
	 * @return resulting array with property (*)
	 */
	abstract public Iterator<Job> schedule();
	
	public void getJobs(Iterator<Job> it) {
		throw new UnsupportedOperationException();
	}
}
