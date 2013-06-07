package solutions;

public class HeapMaxRevenge<T> extends HeapRevenge<T> {
	
	@Override
	public int climb() {
		int elementPos = heapArray.size() - 1;
		
		while (heapArray.get(getParent(elementPos)).getKey() < heapArray.get(elementPos).getKey()) {
			swap(elementPos, getParent(elementPos));
			elementPos = getParent(elementPos);
		}
		return elementPos;
	}

	@Override
	public boolean increasePriority(T value, int key) {
		Integer node = nodeByValue(value);
		
		if (node == null || heapArray.get(node).getKey() > key)
			return false;
		
		heapArray.get(node).setKey(key);
		
		while (heapArray.get(getParent(node)).getKey() < heapArray.get(node).getKey()) {
			swap(node, getParent(node));
			node = getParent(node);
		}
		
		return true;
	}

	@Override
	public void initializeHeap() {
		heapArray.add(new Node<T>(Integer.MAX_VALUE, Integer.MAX_VALUE, null));
	}
	
	@Override
	public void heapfy(int i) {
		if (heapArray.size() == 1)
			return;
		
		int left = getLeftChild(i);
		int right = getRightChild(i);
		int leftKey = left < heapArray.size() ? heapArray.get(left).getKey() : -Integer.MAX_VALUE;
		int rightKey = right < heapArray.size() ? heapArray.get(right).getKey() : -Integer.MAX_VALUE;
		
		int maxKey = leftKey >= rightKey ? leftKey : rightKey ;
		int maxPos = leftKey >= rightKey ? left : right ;

		if (maxKey > heapArray.get(i).getKey()) {
			swap(maxPos, i);
			heapfy(maxPos);
		}		
	}
}
