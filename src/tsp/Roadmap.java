package tsp;

import java.util.Arrays;

public class Roadmap {

	private float[] xs;
	private float[] ys;
	
	private int source;
	
	public Roadmap(float[] xs, float[] ys, int source) {
		if (xs.length != ys.length) {
			throw new IllegalStateException("coordinates not match");
		}
		
		this.source = source;
		this.xs = new float[xs.length];
		this.ys = new float[ys.length];
		System.arraycopy(xs, 0, this.xs, 0, xs.length);
		System.arraycopy(ys, 0, this.ys, 0, ys.length);
	}
	
	public float distance(int from, int to) {
		assert(from > 0);
		assert(to > 0);
		assert(from < size());
		assert(to < size());
		
		float diffX = xs[from] - xs[to];
		float diffY = ys[from] - ys[to];
		
		return (float) Math.sqrt(diffX * diffX + diffY * diffY);
	}
	
	public int source() {
		return source;
	}
	
	public int size() {
		return xs.length;
	}
	
	public String toString() {
		return Arrays.toString(xs) + "\n" + Arrays.toString(ys);
	}
}
