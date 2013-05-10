package solutions;

import java.util.ArrayList;

public abstract class Heap {

	protected ArrayList<Node> nodes;
	
	public Heap(int id, int value){
		this.nodes = new ArrayList<Node>();
		this.nodes.add(new Node(id, value));
	}
	
	public Heap(Node node){
		this.nodes = new ArrayList<Node>();
		this.nodes.add(node);
	}

	protected abstract void climber(Node node);

	protected abstract Node appropriateNode(Node nodeA, Node nodeB);
	
	public Node getRoot(){
		return nodes.get(0);
	}
	
	public Node getLast(){
		return nodes.get(nodes.size());
	}
	
	private void removeLast(){
		nodes.remove(nodes.size());
	}
	
	public void setRoot(Node node){
		nodes.set(0, node);
	}
	
	public Node getParent(int index){
		return nodes.get(index/2);
	}
	
	protected Node getLeftChild(Node node){
		return getChild(nodes.indexOf(node)*2);
	}
	
	protected Node getRightChild(Node node){
		return getChild((nodes.indexOf(node)*2)+1);
	}
	
	private Node getChild(int index_child){
		if (index_child >= nodes.size())
			return null;
		
		return nodes.get(index_child);
	}
	
	protected Node compareChildren(Node node){
		Node leftChild = getLeftChild(node);
		Node rightChild = getRightChild(node);

		if (leftChild == null && rightChild == null)		
			return null;
		else if (leftChild == null)
			return rightChild;
		else if (rightChild == null)
			return rightChild;
		else 
			return appropriateNode(leftChild, rightChild);
	}
	
	protected void heapfy(Node node){
		Node child = compareChildren(node);
		if (child == null)
			return;
		else {
			swap(child, node);
			heapfy(node);
		}
	}
	
	public void swap(Node node, Node parent){
		int indexNode = nodes.indexOf(node);
		nodes.set(nodes.indexOf(parent), node);
		nodes.set(indexNode, parent);
	}
	
	public boolean search(int value){
		for (Node node : nodes) {
			if(node.getValue() == value)
				return true;
		}
		return false;
	}
	
	public void insert(Node node){
		if(search(node.getValue()))
			return;
		
		nodes.add(node);
		
		climber(node);
	}
	
	public Node extract(){
		Node extracted = getRoot();
		Node last = getLast();
		
		removeLast();
		setRoot(last);
		
		heapfy(getRoot());
		
		return extracted;
	}
	
	public void print(){
		for (Node node : nodes) {
			System.out.println(node.getId() + "::" + node.getValue());	
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
