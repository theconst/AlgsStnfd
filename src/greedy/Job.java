package greedy;

public class Job {
	private int id;
	private int weight;
	private int length;
	
	public Job(int id, int weight, int length) {
		this.id = id;
		this.weight = weight;
		this.length = length;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return sb.append("[").append(id).append("] ")
				.append("{l=").append(length).append(", w=").append(weight).append("}").toString();
	}
	
}
