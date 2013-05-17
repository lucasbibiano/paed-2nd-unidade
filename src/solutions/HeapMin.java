package solutions;

public class HeapMin extends Heap {

	public HeapMin(int id, int value){
		super(id, value);
	}
	
	public HeapMin(Node node){
		super(node);
	}
	
	protected void climber(Node node){
		if(getRoot() == node)
			return;
		
		while(getParent(node.getPosition()).compareTo(node.getValue()) > 0){
			swap(node, getParent(nodes.indexOf(node)));
		}
	}
	
	protected Node appropriateNode(Node nodeA, Node nodeB) {
		if(nodeA.compareTo(nodeB.getValue()) < 0)
			return nodeA;
		else 
			return nodeB;
	}
}
