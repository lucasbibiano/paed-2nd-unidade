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
		
		while(getParent(nodes.indexOf(node)).getValue() < node.getValue()){
			swap(node, getParent(nodes.indexOf(node)));
		}
	}
}
