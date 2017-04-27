package greedy;

import java.util.ArrayList;
import java.util.List;

public class JobReader extends common.IntParser {
	
	public JobReader(String pathToFile) throws Exception {
		super(pathToFile);
	}
	
	/** 
	 * reads all the input according to the format:
	 * [number_of_jobs]
	 *  [job_1_weight] [job_1_length]
	 *  [job_2_weight] [job_2_length]
    */
	public List<Job> getAllJobs() {
		int size = ints.get(0);							// read number of jobs
		ArrayList<Job> jobs = new ArrayList<>(size);	// returned list of jobs
		
		for (int i = 1; i < ints.size() - 1; i += 2) {
			
			/* read jobs according to the format, assigning id's starting from one */
			jobs.add(new Job(i, ints.get(i), ints.get(i + 1)));
		}
		
		return jobs;
	}
}
