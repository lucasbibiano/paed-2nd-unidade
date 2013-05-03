package solutions;

import java.util.ArrayList;

public class Heap {

	private ArrayList<Node> nodes;
	private Type type;
	
	private enum Type { MAX, MIN };
	public static final Type MIN = Type.MIN;
	public static final Type MAX = Type.MAX;
	
	public Heap(int id, int value, Type type){
		this.nodes = new ArrayList<Node>();
		this.nodes.add(new Node(id, value));
		
		this.type = type;
	}
	
	public Heap(Node node, Type type){
		this.nodes = new ArrayList<Node>();
		this.nodes.add(node);
		
		this.type = type;
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
