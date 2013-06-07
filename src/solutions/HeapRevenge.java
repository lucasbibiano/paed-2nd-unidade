package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.javatuples.Pair;

public abstract class HeapRevenge<T> {
	protected ArrayList<Node<T>> heapArray;
	private HashMap<T, Integer> valueToPos;
	
	private boolean acceptDuplicates;
	
	public HeapRevenge() {
		heapArray = new ArrayList<Node<T>>();
		valueToPos = new HashMap<T, Integer>();
		
		initializeHeap();
	}
	
	public abstract int climb();
	public abstract void initializeHeap();
	public abstract void heapfy(int i);
	public abstract boolean increasePriority(T value, int key);
	
	public boolean insert(T value, int key) {
		if (!acceptDuplicates && valueToPos.containsKey(value))
			return false;
		
		heapArray.add(new Node<T>(heapArray.size() - 1, key, value));
		
		int elementPos = climb();
				
		valueToPos.put(value, elementPos);
		
		return true;
	}
	
	public Pair<Integer, T> extract() {
		if (heapArray.size() == 1)
			return null;
						
		T result = heapArray.get(1).value;
		int resultKey = heapArray.get(1).getKey();
		
		swap(1, heapArray.size() - 1);
		
		heapArray.remove(heapArray.size() - 1);
		
		valueToPos.remove(result);
		
		heapfy(1);
		
		return new Pair<Integer, T>(resultKey, result);
	}
	
	protected Integer nodeByValue(T value) {
		return valueToPos.get(value);
	}
	
	protected int getLeftChild(int i) {
		return 2*i;
	}
	
	protected int getRightChild(int i) {
		return 2*i + 1;
	}
	
	protected void swap(int i, int parent) {
		valueToPos.put(heapArray.get(i).value, parent);
		valueToPos.put(heapArray.get(parent).value, i);
		
		Collections.swap(heapArray, i, parent);
	}

	protected int getParent(int i) {
		return i/2;
	}

	public boolean isAcceptDuplicates() {
		return acceptDuplicates;
	}


	public void setAcceptDuplicates(boolean acceptDuplicates) {
		this.acceptDuplicates = acceptDuplicates;
	}

	protected class Node<T> {
		private int position;
		private T value;
		private int key;
		
		public Node(int position, int key, T value) {
			this.position = position;
			this.setKey(key);
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "[" + position + ", " + getKey() + ", " + value + "]";
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}
	}
}
