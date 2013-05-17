package solutions;

public class Node<T> implements Comparable<T> {

	private int id;
	private T value;
	private int position;
	
	public Node(int id, T value) {
		this.id 		= id;
		this.value 		= value;
		this.position 	= 0;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(T value) {
		if ((Integer) this.value > (Integer) value)
			return 1;
		else if ((Integer) this.value < (Integer) value)
			return -1;
		else
			return 0;
	}
}