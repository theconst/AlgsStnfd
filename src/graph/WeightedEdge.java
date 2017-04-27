package graph;

public class WeightedEdge implements Comparable<WeightedEdge> {
	private double weight;
	private int from;
	private int to;
	
	public WeightedEdge(int from, int to, double weight) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
	
	// auto getters/setters
	public double weight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int from() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int to() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}

	
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + this.from;
	    result = prime * result + this.to;
	    long temp;
	    temp = Double.doubleToLongBits(this.weight);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    WeightedEdge other = (WeightedEdge) obj;
	    if (this.from != other.from)
		    return false;
	    if (this.to != other.to)
		    return false;
	    if (Double.doubleToLongBits(this.weight) != Double
	            .doubleToLongBits(other.weight))
		    return false;
	    return true;
    }

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.append("e{").append(from).append("->").append(to)
				.append(", ").append(weight).append("}").toString();
	}

	@Override
    public int compareTo(WeightedEdge that) {
	    return Double.compare(that.weight, this.weight);
    }
}
