package twosum;

import java.util.HashSet;

public class TwoSumFinder {
	
	private Long[] numbers = null;
	
	private Long[] targets = null;
	
	private HashSet<Long> numbersTable = new HashSet<>();
	
	private HashSet<Long> sumsTable = new HashSet<>();
	
	private int count = -1;
	
	//! shallow copy
	public TwoSumFinder(Long[] numbers, Long[] targets) {
		this.numbers = numbers;
		this.targets = targets;
	}
	
	
	public void findCount() {
		count = 0;
		
		for (Long n: numbers) {
			numbersTable.add(n);
		}
		
		for (Long t : targets) {
			for (Long n : numbers) {
				Long diff = t - n;
				if ((diff != n) && numbersTable.contains(t - n)) {
					sumsTable.add(t);									//count numbers
				}
			}
		}
	}
	
	public int getCount() {
		if (count < 0) {
			throw new IllegalArgumentException("initialize array first");
		}

		return sumsTable.size();
	}
}
