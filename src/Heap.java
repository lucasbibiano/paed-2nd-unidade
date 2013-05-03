
public class Heap<T> {

	private Node<T> root;
	private String type;
	
	public Heap(int key, T value){
		this.root = new Node<T>(value, key);
	}
	
	public Heap(Node<T> node, String type){
		this.root = node;
	}
	
	public static void main(String[] args) {

	}

}
