public class Node<T> {

	private T value;
	private int key;
	
	private Node<T> left;
	private Node<T> right;
	
	public Node(T value, int key) {
		this.value 	= value;
		this.key 	= key;
		this.left 	= null;
		this.right 	= null;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
}
