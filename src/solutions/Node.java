package solutions;

public class Node {

	private int id;
	private int value;
	
	public Node(int id, int value) {
		this.id 	= id;
		this.value 	= value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
