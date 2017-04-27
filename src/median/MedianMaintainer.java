package median;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianMaintainer {

	
	private int size;
	
	
	private int[] medianByStep;
	
	public MedianMaintainer(Integer[] sequence) {
		this.size = sequence.length;
		this.medianByStep = new int[size];
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(10000, new Comparator<Integer>() {

			@Override
            public int compare(Integer x, Integer y) {
				return Integer.compare(x, y);
            }	
		});
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10000,  new Comparator<Integer>() {

			@Override
            public int compare(Integer x, Integer y) {
				return Integer.compare(y, x);
			}
         });
		
		int steps = 0;
		minHeap.add(medianByStep[steps] = sequence[steps]);
		steps++;
		
		while (steps < size) {
			//add element to heaps

			int next = sequence[steps];
			// add element to larger heap or to smaller heap
			if (next <= minHeap.peek()) {
				maxHeap.add(next);
			} else {
				minHeap.add(next);
			}
			
			//balanceHeaps	
			if (maxHeap.size() - minHeap.size() > 1) {
				minHeap.add(maxHeap.poll());
			}
			
			if (minHeap.size() > maxHeap.size()) {
				maxHeap.add(minHeap.poll());
			}
			
			//find median on the current step
			medianByStep[steps] = maxHeap.peek();
					
			//go to next step
			steps++;
		}
	}
	
	//!shallow copy
	public int[] getMedianByStep() {
		return medianByStep;
	}
}
