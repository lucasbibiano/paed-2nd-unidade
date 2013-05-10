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
		
		while(getParent(nodes.indexOf(node)).getValue() > node.getValue()){
			swap(node, getParent(nodes.indexOf(node)));
		}
	}
}
