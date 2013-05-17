package solutions;

import java.util.ArrayList;

public abstract class Heap<T> {

	protected ArrayList<Node<T>> nodes;
	
	public Heap(int id, int value){
		this.nodes = new ArrayList<Node<T>>();
		this.nodes.add(new Node(id, value));
	}
	
	public Heap(Node<T> node){
		this.nodes = new ArrayList<Node<T>>();
		this.nodes.add(node);
	}

	protected abstract void climber(Node<T> node);

	protected abstract Node<T> appropriateNode(Node<T> nodeA, Node<T> nodeB);
	
	public Node<T> getRoot(){
		return nodes.get(0);
	}
	
	public Node<T> getLast(){
		return nodes.get(nodes.size());
	}
	
	private void removeLast(){
		nodes.remove(nodes.size());
	}
	
	public void setRoot(Node<T> node){
		nodes.set(0, node);
	}
	
	public Node<T> getParent(int index){
		return nodes.get(index/2);
	}
	
	protected Node<T> getLeftChild(Node<T> node){
		return getChild(node.getPosition()*2);
	}
	
	protected Node<T> getRightChild(Node<T> node){
		return getChild((node.getPosition()*2)+1);
	}
	
	private Node<T> getChild(int index_child){
		if (index_child >= nodes.size())
			return null;
		
		return nodes.get(index_child);
	}
	
	protected Node<T> compareChildren(Node<T> node){
		Node<T> leftChild = getLeftChild(node);
		Node<T> rightChild = getRightChild(node);

		if (leftChild == null && rightChild == null)		
			return null;
		else if (leftChild == null)
			return rightChild;
		else if (rightChild == null)
			return rightChild;
		else 
			return appropriateNode(leftChild, rightChild);
	}
	
	protected void heapfy(Node<T> node){
		Node<T> child = compareChildren(node);
		if (child == null)
			return;
		else {
			swap(child, node);
			heapfy(node);
		}
	}
	
	public void swap(Node<T> node, Node<T> parent){
		int indexNode = node.getPosition();
		int indexParent = parent.getPosition();
		
		nodes.set(indexParent, node);
		nodes.set(indexNode, parent);
		
		node.setPosition(indexParent);
		parent.setPosition(indexNode);
	}
	
	public boolean search(T value){
		for (Node<T> node : nodes) {
			if(node.compareTo(value) == 0)
				return true;
		}
		return false;
	}
	
	public Node<T> searchId(int id){
		for (Node<T> node : nodes) {
			if(node.getId() == id)
				return node;
		}
		return null;
	}
	
	public void insert(Node<T> node){
		if(search(node.getValue()))
			return;
		
		nodes.add(node);
		
		climber(node);
	}
	
	public Node<T> extract(){
		Node<T> extracted = getRoot();
		Node<T> last = getLast();
		
		removeLast();
		setRoot(last);
		
		heapfy(getRoot());
		
		return extracted;
	}
	
	public void update(int id, T value){
		Node<T> find = searchId(id);
		
		if (find == null)
			return;
		
		find.setValue(value);
		climber(find);
	}
	
	public void print(){
		for (Node<T> node : nodes) {
			System.out.println("id:" + node.getId() + "|value:" + node.getValue() + "|position:" + node.getPosition());	
		}
	}
	
	public static void main(String[] args) {

		Heap heapinho = new HeapMax(1,1);
		
		for (int i=2; i<12; i++){
			heapinho.insert(new Node(i, i));
		}
		
		heapinho.print();
	}

}
