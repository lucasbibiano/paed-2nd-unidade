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
	
	public void swap(Node node, Node parent){
		int indexNode = nodes.indexOf(node);
		nodes.set(nodes.indexOf(parent), node);
		nodes.set(indexNode, parent);
	}
	
	protected abstract void climber(Node node);
	
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
		
		//TODO
		//aplicar max ou min heapfy no vetor de nos
		
		return extracted;
	}
	
	public static void main(String[] args) {

	}

}
