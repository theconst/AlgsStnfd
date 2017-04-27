package greedy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

public class Runner {
	
	private static long weightedCompletionTimes(Iterator<Job> schedule) {
		long result = 0; 						// accumulator variable for weightedCompletionTimes
		long timeElapsed = 0;					// time from the completion of all the jobs including given
		
		while(schedule.hasNext()) {
			Job job = schedule.next();
			timeElapsed += job.getLength();
			result += job.getWeight() * timeElapsed;
		}
		
		return result;
	}
	

	private static void writeSchedule(OutputStreamWriter out,
			Iterator<Job> schedule) throws IOException {
		while (schedule.hasNext()) {
			Job job = schedule.next();
			out.write(job.toString() + System.lineSeparator());
		}
	}
	
	
	/**
	 * Quick and dirty runner for the application
	 * 
	 * @param args		 [input text file] [output text file]
	 * @throws Exception 
	 */
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
		
		
		//read jobs from the input
		List<Job> jobs = new JobReader(args[0]).getAllJobs();
		
		//perform scheduling
		Scheduler suboptimalScheduler = new DifferenceScheduler(jobs.iterator());
		Scheduler optimalScheduler = new ProductScheduler(jobs.iterator());
		
		
		//write the output to the specified text file
		out.write("Suboptimal schedule is:" + System.lineSeparator());
		writeSchedule(out, suboptimalScheduler.schedule());
		out.write("score: " + weightedCompletionTimes(suboptimalScheduler.schedule())
				+ System.lineSeparator());
		out.write("Optimal schedule is:" + System.lineSeparator());
		writeSchedule(out, optimalScheduler.schedule());
		out.write("score: " + weightedCompletionTimes(optimalScheduler.schedule()));
		
		//flush and close
		out.close();
	}
}
