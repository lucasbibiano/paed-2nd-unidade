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
	
	public Node extract(){
		//extrair o root
		Node extracted = nodes.get(0);
		
		return extracted;
	}
	
	public static void main(String[] args) {

	}

}
