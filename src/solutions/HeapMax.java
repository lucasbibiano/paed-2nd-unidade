package solutions;

public class HeapMax extends Heap {

	public HeapMax(int id, int value){
		super(id, value);
	}
	
	public HeapMax(Node node){
		super(node);
	}
	
	protected void climber(Node node){
		if(getRoot() == node)
			return;
		
		while(getParent(node.getPosition()).compareTo(node.getValue()) < 0){
			swap(node, getParent(nodes.indexOf(node)));
		}
	}
	
	protected Node appropriateNode(Node nodeA, Node nodeB) {
		if(nodeA.compareTo(nodeB.getValue()) > 0)
			return nodeA;
		else 
			return nodeB;
	}
}
